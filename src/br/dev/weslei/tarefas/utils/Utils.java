package br.dev.weslei.tarefas.utils;

public class Utils {
	private static int contador = 0; 
	
		
	public static  synchronized String gerarTDSequencial() {
		contador++;
		return String.format("%06d", contador);
	}

}
