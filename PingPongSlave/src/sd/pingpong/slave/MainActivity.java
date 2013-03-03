package sd.pingpong.slave;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import sd.pingpong.slave.R;


import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.hardware.*;

public class MainActivity extends Activity implements SensorEventListener {

	private TextView tvx;
	private TextView tvy;
	private TextView tvz;
	private ToggleButton tbt;
	private EditText etip;
	private EditText etporta;
	private SensorManager SensorManager;
	private Sensor Accelerometer;
	private final float NOISE = (float) 0.30;
	private float x = (float) 0.0;
	private float y = (float) 0.0;
	private float z = (float) 0.0;
	private Connection con = null;
	private String ip;
	private String porta;
	private final String DEFAULTIP= "192.168.1.129";
	private final String DEFAULTPORTA = "6900";
	private float v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		tvx= (TextView)findViewById(R.id.x);
		tvy= (TextView)findViewById(R.id.y);
		tvz= (TextView)findViewById(R.id.z);
		etip = (EditText) findViewById(R.id.IPEditText);
		etporta = (EditText) findViewById(R.id.portaEditText);
		tbt = (ToggleButton) findViewById(R.id.toggleButton1);
		tvx.setText("0.0");
		tvy.setText("0.0");
		tvz.setText("0.0");
		etip.setText(DEFAULTIP);
		etporta.setText(DEFAULTPORTA);
		SensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Accelerometer = SensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		SensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	protected void onResume(){
		super.onResume();
		SensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	protected void onPause(){
		SensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onSensorChanged(SensorEvent event){
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
		if(x<NOISE) x = (float) 0.0;
		if(y<NOISE) y = (float) 0.0;
		if(z<NOISE) z = (float) 0.0;
		tvx.setText(Float.toString(x));
		tvy.setText(Float.toString(y));
		tvz.setText(Float.toString(z));	
		v = z;
		if(con!=null){
			try{
				System.out.println("TESTE");
				con.sendData(String.valueOf(v));
				con.errorcounter=0;
			}catch(IOException e){
				System.err.println("Falha na comunicação com o Servidor");
				con.errorcounter++;
				if(con.errorcounter==10){
					System.err.println("Conexão perdida");
					tbt.setChecked(false);
					handleConnectionError();
				}
			}
		}
	}
	
	private void handleConnectionError(){
		int counter=0;
		System.out.println("Tentando reconectar...");
		while(true){
			counter++;
			System.out.println("Tentativa:" + counter);
		    try{
		    	con.closeConnection();
		    	System.out.println("TESTE");
		   		con = new Connection(ip,Integer.parseInt(porta));
		   		System.out.println("Reconectado!");
		   		tbt.setChecked(true);
		   		break;
		   	}catch(UnknownHostException e1){
				System.err.println("Host não encontrado.");
		   	}catch(SocketTimeoutException e1){
		   		System.err.println("Falha na conexão: servidor não respondeu");
			}catch (IOException e1) {
	            System.err.println("Erro de I/O durante conexão.");
			}finally{
				if(counter>=5){
					System.err.println("Não foi possível restabelecer conexão.");
					if(con!=null) con=null;
					break;
				}
			}
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	public void onToggleClicked(View view) {
	    // Is the toggle on?
		ToggleButton tb = ((ToggleButton) view);
		tb.toggle();
	    boolean on = tb.isChecked();
	    ip=etip.getText().toString();
	    porta=etporta.getText().toString();
	    if (!on) {
	    	try{
	    		con = new Connection(ip,Integer.parseInt(porta));
	    		tb.setChecked(true);
	    	}catch(UnknownHostException e){
				System.err.println("Host não encontrado.");
			}catch(SocketTimeoutException e1){
		   		System.err.println("Falha na conexão: servidor não respondeu");
			}catch (IOException e) {
	            System.err.println("Erro de I/O durante conexão.");
			}
	    }
	    else {
	    		con.closeConnection();
	    		con=null;
	    		tb.setChecked(false);
	    }
	}
	

}
