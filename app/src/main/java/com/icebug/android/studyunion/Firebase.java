//package com.icebug.android.studyunion;
//
//import android.util.Log;
//
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//
//import static com.google.android.gms.internal.zzs.TAG;
//
///**
// * Created by nafis on 13-May-17.
// */
//
//public class Firebase extends FirebaseInstanceIdService {
//
//@Override
//    public void onTokenRefresh(){
//
//    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//    Log.d(TAG, "Refreshed token: " +refreshedToken);
//
//    sendRequestToServer(refreshedToken);
//
//
//   }
//
//}
