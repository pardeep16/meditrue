package in.ideveloper.meditrue;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by pardeep on 30-10-2016.
 */
public class FirebaseInstanceServies extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseInstanceServies ";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        //Displaying token on logcat
  //      Log.d(TAG, "Refreshed token: " + refreshedToken);

        System.out.println( "Refreshed token: " + refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}
