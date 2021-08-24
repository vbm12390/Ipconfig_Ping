package view;

import controller.RedesController;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
	RedesController network = new RedesController();
	int opc = 0;
		while (opc!=3) {
		opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Obter informacões de sua interface de rede \n2- Testar Conexão de Internet \n3 - Finalizar Programa "));
	
		switch (opc) {
			case 1: network.interfaces(opc);
					break;
			case 2: network.interfaces(opc);
					break;
			case 3: JOptionPane.showMessageDialog(null, "Programa Finalizado");;
					break;
			default: JOptionPane.showMessageDialog(null, "Opção Invalida");
			}
		}
	}
}