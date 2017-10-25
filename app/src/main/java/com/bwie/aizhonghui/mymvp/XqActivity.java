package com.bwie.aizhonghui.mymvp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.aizhonghui.mymvp.model.LoginModel;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.CircleRoundImageView;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;

public class XqActivity extends AppCompatActivity implements View.OnClickListener,LoginView {
    private CircleRoundImageView cvtou;
    private static int CAMERA_REQUEST_COOE = 1;
    private LoginModel loginModel;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;
    private ImageView ivjian;
    private TextView tvuser;
    private TextView nkname;
    private RelativeLayout re_ncname;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        initView();
        initData();
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        login = sp.getString("LOGIN", null);
        System.out.println("=================== "+ login);
        if(login !=null){
            loginPresenter.get_user(login +"");
        }else{
            System.out.println("===================还没登录过");
        }
       // initIntent();
    }

    private void initData() {
        loginPresenter=new LoginPresenter(XqActivity.this,this);
    }

    private void initIntent() {
//        Intent intent = getIntent();
//        String result = intent.getStringExtra("result");
//        System.out.println("====传递过来的个人信息属性==="+result);
//        try {
//            JSONObject obj = new JSONObject(result);
//            JSONObject data = obj.optJSONObject("data");
//            String icon = data.optString("icon");
//            ImageLoader.getInstance().displayImage(icon,ivtou);
//            ImageLoader.getInstance().displayImage(icon,cv);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void initView() {
        cvtou= (CircleRoundImageView) findViewById(R.id.iv_toug);
        ivjian = (ImageView) findViewById(R.id.iv_xq_jian);
        tvuser = (TextView) findViewById(R.id.tv_xq_user);
        nkname = (TextView) findViewById(R.id.tv_xq_nickname);
        re_ncname = (RelativeLayout) findViewById(R.id.re_xq_nickname);
        re_ncname.setOnClickListener(this);
        ivjian.setOnClickListener(this);
        cvtou.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_toug:
                showChoosePicDialog();
                break;
            case R.id.re_xq_nickname:
                loginPresenter.update_name(login,"辉");
                nkname.setText("辉");
                break;
            case R.id.iv_xq_jian:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
//        if(data==null){
//           return;
//        }else{
//            Uri uri=data.getData();
//            Toast.makeText(XqActivity.this,uri.toString(),Toast.LENGTH_SHORT).show();
//            Uri fileUri=converUri(uri);
//            System.out.println("========"+fileUri.toString());
//
//
//        }
    }
    private Uri converUri(Uri uri){
        InputStream is=null;
        try {
            is=getContentResolver().openInputStream(uri);
            Bitmap bitmap= BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Uri saveBitmap(Bitmap bm){
        File tmDir=new File(Environment.getExternalStorageDirectory()+"/avaterr");
        if(tmDir.exists()){
            tmDir.mkdir();
        }
        File img=new File(tmDir.getAbsolutePath()+"avater.png");

        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG,85,fos);
            loginModel=new LoginModel(XqActivity.this);
            loginModel.touimg("97",img);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent,CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            // photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            cvtou.setImageBitmap(photo);
            saveBitmap(photo);
        }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void loginSuccess(String code, String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONObject dataj = obj.optJSONObject("data");
            String icon = dataj.optString("icon");
            String nickname = dataj.optString("nickname");
            String username = dataj.optString("username");
            System.out.println("===="+icon);
            System.out.println("====="+username);
            ImageLoader.getInstance().displayImage(icon,cvtou);
            tvuser.setText(username);
            nkname.setText(nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSuccess(String code, String msg) {
        System.out.println("======"+msg);
        Toast.makeText(XqActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
}
