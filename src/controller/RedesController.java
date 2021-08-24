package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RedesController {
	private String getOS() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void interfaces(int opc){
		String os = getOS();
		String process;
		if (opc == 1) {
			if (os.contains("Windows")) {
				process = "ipconfig";
				readProcess(process);
			}else {
				process = "ifconfig";
				readProcess(process);		
			}
		}
		if (opc == 2) {
			if (os.contains("Windows")) {		
				process = "ping -4 -n 10 www.google.com.br";
				System.out.println("Testando o tempo medio do seu ping \nAguarde o tempo está sendo computado ");
				readProcess(process);
			}else {
				process = "ping -4 -c 10 www.google.com.br";
				System.out.println("Testando o tempo medio do seu ping \nAguarde o tempo está sendo computado ");
				readProcess(process);	
			}
		}
	}
		
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				show(process, linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	public void show(String process, String linha){

		
		if (process.contains("ipconfig")) {
			if (linha.contains("Adaptador")) {
				System.out.print(linha+"\n");
			}
			if (linha.contains("IPv4")) {
				String[] adress = linha.split(":");
				System.out.println(adress[1]);
			}	
		}
		
		if (process.contains("ifconfig")) {
			if (linha.contains("flags")) {
				String adapterName = linha.split(":")[0];
				System.out.print(adapterName +" ");
			}
			if (linha.contains("inet ")) {
				String[] inet = linha.split("netmask");
				System.out.println(inet[0].substring(13,24)+ "\n");
			}	
		}
		
		if (process.contains("ping -4 -n 10")) {
			if (linha.contains("dia")) {
				String[] media = linha.split(" = ");
					System.out.println("Media= " + (media[3]));
			}				
		}
		
		if (process.contains("ping -4 -c 10")) {
			if (linha.contains("rtt min/avg/max/mdev")) {
				String[] media = linha.split("/");
					System.out.println("Media =" + (media[4]));
			}				
		}
	}
}
