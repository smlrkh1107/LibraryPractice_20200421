package kr.tjeit.librarypractice_20200421

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        callBtn.setOnClickListener {
            val permissionListener = object : PermissionListener{
                override fun onPermissionGranted() {
//              사용자가 권한을 승인해준 상태일 때,

                    val uri = Uri.parse("tel:01067911107")
                    val myIntent = Intent(Intent.ACTION_CALL, uri)
                    startActivity(myIntent)
                    // 권한설정안하면 앱죽어 >> https://github.com/ParkSangGwon/TedPermission

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//              사용자가 권한을 거부할 때
                    Toast.makeText(mContext, "권한 허용 필요",Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.with(mContext)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("설정 > 권한에서 허용으로 바꿔주세요")
                .setPermissions(android.Manifest.permission.CALL_PHONE)
                .check()



        }


    }

    override fun setValue() {

        Glide.with(mContext).load("http://pds.joins.com/news/component/htmlphoto_mmdata/201810/29/htm_2018102914510995660.jpeg").into(profileImg)

    }

}
