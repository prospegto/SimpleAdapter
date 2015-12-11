package es.iesnervion.android.ignacio.adaptadorlistview;

public class Equipos {

	private String nombre;
	private String entrenador;
	private int escudo;
	private boolean seleccionado;

	public Equipos (String nombre, String entrenador, int escudo) {
		this.nombre = nombre;
		this.entrenador = entrenador;
		this.escudo = escudo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public int getEscudo() {
		return escudo;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}



}
