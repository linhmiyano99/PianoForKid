package com.example.pianoforkid.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;

public class SaveMusicActivity extends AppCompatActivity implements View.OnClickListener {

	Button button_record;
	int record_status = 0;
	Sound sound;
	long lastDown = 0;
	long lastDuration = 0;
	int note;
	List<Sound> song;
	int songId;
	int position;
	int songLeft = 0;
	SongViewModel viewModel;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btn10;
	Button btn11;
	//Button btn12;
	//Button btn13;
	//Button btn14;
	Animation anim_not_nhac_2;

	ImageView imageA;
	ImageView imageB;
	ImageView imageC;
	ImageView imageD;
	ImageView imageE;
	ImageView imageF;
	ImageView imageG;
	//ImageView imageA2;
	//ImageView imageB2;
	ImageView imageC2;
	ImageView imageD2;
	ImageView imageE2;
	ImageView imageF2;
	//ImageView imageG2;

	String sheet;

	float onUp;
	float onDown;
	int lastNote = -1;

	KeyNote keyNote;
	TextView txt_note;

	public static void startActivity(Context context){
		Intent intent = new Intent(context, SaveMusicActivity.class);
		context.startActivity(intent);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_music);

		Log.d("SaveMusicActivity", "onCreate");

		keyNote = new KeyNote(this.getApplication());
		anim_not_nhac_2= AnimationUtils.loadAnimation(this,R.anim.anim_key_board);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		ImageButton button_back = findViewById(R.id.button_back);
		button_back.setOnClickListener(v -> onBackPressed());

		viewModel = new ViewModelProvider(this).get(SongViewModel.class);
		button_record = findViewById(R.id.button_record);
		button_record.setOnClickListener(this::button_recordOnClick);

		song = new ArrayList<>();
		position = 0;

		viewModel.getLastSongId().observe(this, integer -> {
			songId = integer + 1;
			resetSetSong();
			sheet = "";
		});

		btn1 = findViewById(R.id.btnC1);
		btn2 = findViewById(R.id.btnD1);
		btn3 = findViewById(R.id.btnE1);
		btn4 = findViewById(R.id.btnF1);
		btn5 = findViewById(R.id.btnG1);
		btn6 = findViewById(R.id.btnA1);
		btn7 = findViewById(R.id.btnB1);
		btn8 = findViewById(R.id.btnC2);
		btn9 = findViewById(R.id.btnD2);
		btn10 = findViewById(R.id.btnE2);
		btn11 = findViewById(R.id.btnF2);
		//btn12 = findViewById(R.id.btnG2);
		//btn13 = findViewById(R.id.btnA2);
		//btn14 = findViewById(R.id.btnB2);

		btn1.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn2.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn3.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn4.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn5.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn6.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn7.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn8.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn9.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn10.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});
		btn11.setOnTouchListener((v, event) -> {
			switch (event.getAction())
			{
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_DOWN:
					touch(v);
					break;
				case MotionEvent.ACTION_UP:
					break;
				default: break;
			}
			return false;
		});

		
		//btn12.setOnClickListener(this);
		//btn13.setOnClickListener(this);
		//btn14.setOnClickListener(this);

		imageC = findViewById(R.id.imageView1);
		imageD = findViewById(R.id.imageView2);
		imageE = findViewById(R.id.imageView3);
		imageF = findViewById(R.id.imageView4);
		imageG = findViewById(R.id.imageView5);
		imageA = findViewById(R.id.imageView6);
		imageB = findViewById(R.id.imageView7);
		imageC2 = findViewById(R.id.imageView8);
		imageD2 = findViewById(R.id.imageView9);
		imageE2 = findViewById(R.id.imageView10);
		imageF2 = findViewById(R.id.imageView11);
		//imageG2 = findViewById(R.id.imageView12);
		//imageA2 = findViewById(R.id.imageView13);
		//imageB2 = findViewById(R.id.imageView14);

		txt_note = findViewById(R.id.txt_note);
		txt_note.setText(String.valueOf(songLeft));

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Log.d("onWindowFocusChanged", "xx");

		onUp = (int) imageA.getY();
		//onUp2 = (int) imageA2.getY();
		onDown = onUp - 100;

	}

	void save(String songName) {
		Song _song = new Song(songId, songName);
		_song.sheet = sheet;
		viewModel.insertSong(_song, song);
		viewModel.insertSong(_song);
		Log.d("Song", String.valueOf(song));
		Log.i("SAVE", "pressed");

	}

	@Override
	public void onClick(View view) {
		Log.d("onClick_sound", String.valueOf(lastDown));
		if(record_status == 1){
			songLeft++;
			txt_note.setText(String.valueOf(songLeft));
		}
		if (lastDown > 0) {
			lastDuration = System.currentTimeMillis() - lastDown;
			sound.setDuration(lastDuration);
			sheet += (lastDuration + ")");
			song.add(sound);
			Log.d("Sound", String.valueOf(sound));
		}
		switch (view.getId()) {
			case R.id.btnD1:
				note = 2;
				break;
			case R.id.btnE1:
				note = 3;
				break;
			case R.id.btnF1:
				note = 4;
				break;
			case R.id.btnG1:
				note = 5;
				break;
			case R.id.btnA1:
				note = 6;
				break;
			case R.id.btnB1:
				note = 7;
				break;
			case R.id.btnC2:
				note = 8;
				break;
			case R.id.btnD2:
				note = 9;
				break;
			case R.id.btnE2:
				note = 10;
				break;
			case R.id.btnF2:
				note = 11;
				break;
			/*case R.id.btnG2:
				note = 12;
				break;
			case R.id.btnA2:
				note = 13;
				break;
			case R.id.btnB2:
				note = 14;
				break;*/

			default:
				note = 1;
				break;
		}

		sound = new Sound();
		lastDown = System.currentTimeMillis();
		sound.setNote(note);
		sheet += ("(" + note +",");
		Log.d("XXX", String.valueOf(note));

		check(note);
		keyNote.playNote(note);
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	public void button_recordOnClick(View v) {
		Log.d("XXX", "button_recordOnClick");


		if (record_status == 0) {
			button_record.setBackgroundColor(Color.parseColor("#0BC205"));
			button_record.setBackground(this.getResources().getDrawable(R.drawable.pause));
			record_status = 1;
			resetSetSong();
		} else {
			if (record_status == 1) {
				button_record.setBackgroundColor(Color.parseColor("#fa09ad"));
				button_record.setBackground(this.getResources().getDrawable(R.drawable.stop));
				showAlertDialogButtonClicked();
				record_status = 0;
			}
		}
		songLeft = 0;
		txt_note.setText(String.valueOf(songLeft));


	}

	public void showAlertDialogButtonClicked() {
		// create an alert builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Save Your Songs");
		// set the custom layout
		final View customLayout = getLayoutInflater().inflate(R.layout.dialog_save_song, null);
		builder.setView(customLayout);
		// add a button

		Button button_save = customLayout.findViewById(R.id.button_save);
		Button button_cancel = customLayout.findViewById(R.id.button_cancel);
		EditText editText = customLayout.findViewById(R.id.edit_dialog_save);

		// create and show the alert dialog
		AlertDialog dialog = builder.create();
		dialog.show();
		button_save.setOnClickListener(v -> {
			if (lastDown > 0) {
				lastDuration = System.currentTimeMillis() - lastDown;
				sound.setDuration(lastDuration);
				song.add(sound);
			}
			String songName = editText.getText().toString();
			//songName = songName.replaceAll(" ", "_").toLowerCase();
			Log.i("SongName ", songName);
			// Save the new songs
			save(songName);
			sendNotification();
			dialog.cancel();
		});

		button_cancel.setOnClickListener(v -> dialog.cancel());
	}

	// do something with the data coming from the AlertDialog
	private void sendNotification() {
		Toast.makeText(this, "Your songs was save successful", Toast.LENGTH_SHORT).show();
	}
	public void check(int note) {
		resetAllNote();

		keyNote.playNote(note);
		setNoteAni(note);
		clearAni(lastNote);
		Log.d("XXX_image", String.valueOf(note));

		switch (note) {
			case 1:
				imageC.setY(onDown);
				break;
			case 2:
				imageD.setY(onDown);
				break;
			case 3:
				imageE.setY(onDown);
				break;
			case 4:
				imageF.setY(onDown);
				break;
			case 5:
				imageG.setY(onDown);
				break;
			case 6:
				imageA.setY(onDown);
				break;
			case 7:
				imageB.setY(onDown);
				break;
			case 8:
				imageC2.setY(onDown);
				break;
			case 9:
				imageD2.setY(onDown);
				break;
			case 10:
				imageE2.setY(onDown);
				break;
			case 11:
				imageF2.setY(onDown);
				break;
		/*	case 12:
				imageG2.setY(onDown2);
				break;
			case 13:
				imageA2.setY(onDown2);
				break;
			case 14:
				imageB2.setY(onDown2);
				break;*/
		}
		lastNote = note;
	}
	void resetAllNote(){
		imageC.setY(onUp);
		imageD.setY(onUp);
		imageE.setY(onUp);
		imageF.setY(onUp);
		imageG.setY(onUp);
		imageA.setY(onUp);
		imageB.setY(onUp);
		imageC2.setY(onUp);
		imageD2.setY(onUp);
		imageE2.setY(onUp);
		imageF2.setY(onUp);
		/*imageG2.setY(onUp2);
		imageA2.setY(onUp2);
		imageB2.setY(onUp2);*/
	}
	void resetSetSong() {
		lastNote = -1;
		song = new ArrayList<>();
		position = 0;
		lastDown = 0;
		lastDuration = 0;
	}

	void setNoteAni(int note){
		switch (note){
			case 1: btn1.startAnimation(anim_not_nhac_2);break;
			case 2: btn2.startAnimation(anim_not_nhac_2);break;
			case 3: btn3.startAnimation(anim_not_nhac_2);break;
			case 4: btn4.startAnimation(anim_not_nhac_2);break;
			case 5: btn5.startAnimation(anim_not_nhac_2);break;
			case 6: btn6.startAnimation(anim_not_nhac_2);break;
			case 7: btn7.startAnimation(anim_not_nhac_2);break;
			case 8: btn8.startAnimation(anim_not_nhac_2);break;
			case 9: btn9.startAnimation(anim_not_nhac_2);break;
			case 10: btn10.startAnimation(anim_not_nhac_2);break;
			case 11: btn11.startAnimation(anim_not_nhac_2);break;
			//case 12: btn12.startAnimation(anim_not_nhac_2);break;
			//case 13: btn13.startAnimation(anim_not_nhac_2);break;
			//case 14: btn14.startAnimation(anim_not_nhac_2);break;
		}
	}
	void clearAni(int note){
		switch (note){
			case 1 : btn1.clearAnimation();break;
			case 2 : btn2.clearAnimation();break;
			case 3 : btn3.clearAnimation();break;
			case 4 : btn4.clearAnimation();break;
			case 5 : btn5.clearAnimation();break;
			case 6 : btn6.clearAnimation();break;
			case 7 : btn7.clearAnimation();break;
			case 8 : btn8.clearAnimation();break;
			case 9 : btn9.clearAnimation();break;
			case 10 : btn10.clearAnimation();break;
			case 11 : btn11.clearAnimation();break;
			//case 12 : btn12.clearAnimation();break;
			//case 13 : btn13.clearAnimation();break;
			//case 14 : btn14.clearAnimation();break;
		}
	}

	public void touch(View view) {
		Log.d("onClick_sound", String.valueOf(lastDown));
		if(record_status == 1){
			songLeft++;
			txt_note.setText(String.valueOf(songLeft));
		}
		if (lastDown > 0) {
			lastDuration = System.currentTimeMillis() - lastDown;
			sound.setDuration(lastDuration);
			sheet += (lastDuration + ")");
			song.add(sound);
			Log.d("Sound", String.valueOf(sound));
		}
		switch (view.getId()) {
			case R.id.btnD1:
				note = 2;
				break;
			case R.id.btnE1:
				note = 3;
				break;
			case R.id.btnF1:
				note = 4;
				break;
			case R.id.btnG1:
				note = 5;
				break;
			case R.id.btnA1:
				note = 6;
				break;
			case R.id.btnB1:
				note = 7;
				break;
			case R.id.btnC2:
				note = 8;
				break;
			case R.id.btnD2:
				note = 9;
				break;
			case R.id.btnE2:
				note = 10;
				break;
			case R.id.btnF2:
				note = 11;
				break;
			/*case R.id.btnG2:
				note = 12;
				break;
			case R.id.btnA2:
				note = 13;
				break;
			case R.id.btnB2:
				note = 14;
				break;*/

			default:
				note = 1;
				break;
		}

		sound = new Sound();
		lastDown = System.currentTimeMillis();
		sound.setNote(note);
		sheet += ("(" + note +",");
		Log.d("XXX", String.valueOf(note));

		check(note);
		keyNote.playNote(note);
	}

}
