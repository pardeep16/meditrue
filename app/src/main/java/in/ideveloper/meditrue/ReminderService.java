package in.ideveloper.meditrue;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class ReminderService extends WakeReminderIntentService {

	public ReminderService() {
		super("ReminderService");
			}

	@Override
	void doReminderWork(Intent intent) {
		Log.d("ReminderService", "Doing work.");
		Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);
		String title=intent.getExtras().getString(RemindersDbAdapter.KEY_TITLE);
		 
		NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
						
		Intent notificationIntent = new Intent(this, MedicineNotifyOpen.class);
		notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId);
		notificationIntent.putExtra(RemindersDbAdapter.KEY_TITLE,title);
		
		PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT); 
		
		Notification note=new Notification(R.drawable.pills,"meditrue notification", System.currentTimeMillis());
		note.setLatestEventInfo(this, getString(R.string.notify_new_task_title),"Take Medicine :"+title, pi);

		note.defaults |= Notification.DEFAULT_SOUND; 
		note.flags |= Notification.FLAG_AUTO_CANCEL; 
		
		// An issue could occur if user ever enters over 2,147,483,647 tasks. (Max int value). 
		// I highly doubt this will ever happen. But is good to note. 
		int id = (int)((long)rowId);
		mgr.notify(id, note); 
		
		
	}
}
