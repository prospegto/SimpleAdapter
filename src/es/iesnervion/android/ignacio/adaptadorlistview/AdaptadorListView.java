package es.iesnervion.android.ignacio.adaptadorlistview;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AdaptadorListView extends Activity implements OnItemClickListener {

	private Equipos[] opciones = new Equipos[]{
			new Equipos("Athletic", "Ernesto Valverde", R.drawable.athletic),
			new Equipos("Atletico de Madrid", "Diego Pablo Simeone", R.drawable.atletico),
			new Equipos("FC Barcelona", "Gerardo Martino", R.drawable.bcn),
			new Equipos("Getafe", "Luis García Plaza", R.drawable.getafe),
			new Equipos("Malaga", "Bernd Schuster", R.drawable.malaga),
			new Equipos("Real Madrid", "Carlo Ancelotti", R.drawable.madrid),
			new Equipos("Sevilla", "Unai Emery", R.drawable.sevilla),
			new Equipos("Sevilla", "Unai Emery", R.drawable.sevilla),
			new Equipos("Getafe", "Luis García Plaza", R.drawable.getafe),
			new Equipos("Getafe", "Luis García Plaza", R.drawable.getafe),
			new Equipos("Betis", "Pepe Mel", R.drawable.betis)};

	private TextView lblEtiqueta;
	private ListView listaOpciones;
	private CheckBox chkFila;
	private LinearLayout footer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		lblEtiqueta = (TextView)findViewById(R.id.lblEtiqueta);
		listaOpciones = (ListView) findViewById(R.id.listaOpciones);
		chkFila = (CheckBox) findViewById(R.id.chkFila);
//		View footer = getLayoutInflater().inflate(R.layout.footer, null);
//		View view = getLayoutInflater().inflate(R.layout.footer, listaOpciones, false); 
//	    footer = (LinearLayout) view.findViewById(R.id.footer);
//		View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
//		listaOpciones.addFooterView(footer);
		
		//ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones);
		/* XML*/// ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.valoresOpciones, android.R.layout.simple_list_item_1);
		AdaptadorTitulos adaptador = new AdaptadorTitulos(this);
		listaOpciones.setOnItemClickListener(this);
		//listaOpciones.setSelector(R.drawable.selector);
		listaOpciones.setAdapter(adaptador);	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}


	class AdaptadorTitulos extends ArrayAdapter<Equipos> {

		Activity context;

		AdaptadorTitulos(Activity context) {
			super(context, R.layout.lista_con_titulos, opciones);
			this.context = context;
		}

		public View getView(final int posicion, View convertView, ViewGroup parent) {

			View fila = convertView;
			final ViewHolder holder;

			if (fila == null){
				LayoutInflater inflater = context.getLayoutInflater();
				fila = inflater.inflate(R.layout.lista_con_titulos, parent, false);
				holder = new ViewHolder();
				holder.imgEscudo = (ImageView)fila.findViewById(R.id.imgEscudo);
				holder.titulo = (TextView)fila.findViewById(R.id.lblTitulo);
				holder.subtitulo = (TextView)fila.findViewById(R.id.lblSubtitulo);
				holder.check = (CheckBox) fila.findViewById(R.id.chkFila);
				holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						 Equipos equipos = (Equipos) holder.check.getTag();
						 equipos.setSeleccionado(buttonView.isChecked());
					}
				});
				fila.setTag(holder);
				holder.check.setTag(getItem(posicion));
			}
			else
			{
				holder = (ViewHolder)fila.getTag();
				((ViewHolder) fila.getTag()).check.setTag(getItem(posicion));
			}

			final Equipos filaEquipo = getItem(posicion);
			holder.titulo.setText(filaEquipo.getNombre());
			holder.subtitulo.setText(filaEquipo.getEntrenador());
			holder.imgEscudo.setScaleType(ImageView.ScaleType.CENTER);
			holder.imgEscudo.setImageResource(filaEquipo.getEscudo());
			holder.check.setChecked(filaEquipo.isSeleccionado());

			/*holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					filaEquipo.setSeleccionado(buttonView.isChecked());
					if (filaEquipo.isSeleccionado()) {
						Log.i("CHECK", filaEquipo.getNombre()+" SELECCIONADO");
						filaEquipo.setSeleccionado(!filaEquipo.isSeleccionado());
					} else {
						if (posicion < opciones.length)
							Log.i("UNCHECK", filaEquipo.getNombre()+" DESELECCIONADO");
					}
				}
			});*/


			if (opciones[posicion].getNombre().equals("Sevilla")){
				holder.imgEscudo.setImageResource(R.drawable.bcn);
			}

			return(fila);
		}

	}

	class ViewHolder {
		TextView titulo;
		TextView subtitulo;
		ImageView imgEscudo;
		CheckBox check;

		public ViewHolder(){		
		}

		public ViewHolder(View fila, TextView titulo, TextView subtitulo, ImageView imagen, CheckBox seleccionado){
			this.titulo = (TextView) fila.findViewById(R.id.lblTitulo);
			//this.titulo = titulo;
			this.subtitulo = subtitulo;
			this.imgEscudo = imagen;
			this.check = (CheckBox) fila.findViewById(R.id.chkFila);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> adap, View v, int posicion, long arg3) {
		lblEtiqueta.setText("Equipo seleccionado: " + opciones[posicion].getNombre());
	}

}

