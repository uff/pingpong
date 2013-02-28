package com.example.testeacelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

public class Acelerometro extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	double ax,ay,az;   // these are the acceleration in x,y and z axis
    String textoTela;
    TextView x, y, z;
    Cliente conexao;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        
        //setContentView(R.layout.activity_acelerometro);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    
        x = (TextView) this.findViewById(R.id.x_axis);
	    y = (TextView) this.findViewById(R.id.y_axis);
	    z = (TextView) this.findViewById(R.id.z_axis);
	    
	    //Iniciar Conex�o
	    try {
	    	x.setText("try");
			conexao = new Cliente("192.168.43.231", 6660);
			y.setText("conectou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			x.setText("n�o conectou");
			e.printStackTrace();
			System.out.println("Erro na conex�o.");
		}
	    
	}
	
	@Override
	   public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	/*
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			ax = event.values[0];
			ay = event.values[1];
			az = event.values[2];
			x.setText(ax+"");
			y.setText(ay+"");
			z.setText(az+"");
			try {
				conexao.enviarDados();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Perda de Conex�o");
			}
		}
    */
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acelerometro, menu);
        return true;
    }
    
}
