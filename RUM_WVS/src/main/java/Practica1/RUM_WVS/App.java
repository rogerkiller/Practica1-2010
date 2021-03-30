package Practica1.RUM_WVS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Practica1.RUM_WVS.SuperheroesBBDD;
import Practica1.RUM_WVS.Batallas;
import Practica1.RUM_WVS.Habilidades;
import Practica1.RUM_WVS.Heroes;

public class App 
{
	private static String filePath;
	
    public static void main( String[] args )
    {
    	Scanner input = new Scanner(System.in);

		System.out.println("Sistema de Gestión de Heroes");
		printHelp();
		filePath = "";
		System.out.print("Ruta y nombre del archivo xml: " + filePath);
		SuperheroesBBDD heroes = new SuperheroesBBDD(filePath);
		
		boolean run = true;
		while (run) {
			String inputStr = input.nextLine();

			// Convertir la entrada a comando + argumentos (soporta strings espaciados
			// usando dobles comillas "TEXTO ESPACIADO")
			String command = "";
			List<String> arguments = new ArrayList<String>();
			Matcher match = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(inputStr);

			while (match.find()) {
				String str = match.group(1);
				str = str.replace("\"", "");

				if (command.isEmpty()) {
					command = str;
				} else {
					arguments.add(str);
				}
			}

			// Comando de ayuda
			if (command.equalsIgnoreCase("help")) {
				printHelp();
			}

			// Comando para pasar a JSON
			if (command.equalsIgnoreCase("json")) {
				System.out.println(heroes.toJSON());
			}

			// Comando para cargar archivo
			if (command.equalsIgnoreCase("load")) {
				if (arguments.size() > 0) {
					heroes.loadFile(arguments.get(0));
					System.out.println("Archivo cargado desde " + arguments.get(0));
				} else {
					heroes.loadFile(filePath);
					System.out.println("Archivo cargado desde " + filePath);
				}
			}

			// Comando para guardar archivo
			if (command.equalsIgnoreCase("save")) {
				if (arguments.size() > 0) {
					heroes.saveFile(arguments.get(0));
					System.out.println("Archivo guardado en " + arguments.get(0));
				} else {
					heroes.saveFile(filePath);
					System.out.println("Archivo guardado en " + filePath);
				}
			}

			// Comando para mostrar parte del Almacén
			if (command.equalsIgnoreCase("display")) {
				if (arguments.size() > 0) {
					// Mostrar todo el Almacén
					if (arguments.get(0).equalsIgnoreCase("all")) {
						heroes.printBBDD("BBDD Superheroes", false, true, false);
					}
					// Mostrar base de datos
					if (arguments.get(0).equalsIgnoreCase("heroes")) {
						if (arguments.size() == 1) {
							heroes.printBBDD("BBDD Superheroes", false, false, true);
						} else {
							System.out.println("Introduza:\n display heroes");
						}
					}
					
				} else {
					System.out.println("Introduza:\n display heroes");
				}
			}

			// Comando para Añadir
			if (command.equalsIgnoreCase("add")) {
				if (arguments.size() > 0) {
					// Añadiendo heroe
					if (arguments.get(0).equalsIgnoreCase("heroe")) {
						if (arguments.size() == 5) {
							int i =+ 0;
							heroes.addHeroes(new Heroes(1, arguments.get(1), arguments.get(2), arguments.get(3),
									arguments.get(4), null, null));
							System.out.println("Heroe añadido correctamente!");
							i++;
						} else {
							System.out.println(
									"Introduza:\n add heroe <nombre> <Identidad secreta> <Genero> <Procedencia>");
						}
					}
					// Añadiendo batalla a un heroe
					if (arguments.get(0).equalsIgnoreCase("batalla")) {
						if (arguments.size() == 7) {
							if (heroes.getHeroes().size() > 0) {
								for (int i = 0; i < heroes.getHeroes().size(); i++) {
									if (arguments.get(1).equals(heroes.getHeroes().get(i).getNombreHeroe())) {
										try {
											heroes.getHeroes().get(i).getBatallas()
													.add(new Batallas(Integer.parseInt(arguments.get(2)),arguments.get(3),
															new SimpleDateFormat("dd/MM/yyyy").parse(arguments.get(4)),
															new SimpleDateFormat("dd/MM/yyyy").parse(arguments.get(5))));
										} catch (NumberFormatException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										System.out.println("Batalla para el heroe con nombre ["
												+ heroes.getHeroes().get(i).getNombreHeroe()
												+ "] añadida correctamente!");
									} else {
										System.out.println("Nombre de heroe no encontrado");
									}
								}
							} else {
								System.out.println(
										"No hay Heroes!\n Introduza:\n add heroe <nombre> <Identidad secreta> <Genero> <Procedencia>");
							}
						} else {
							System.out.println(
									"Introduza:\n add heroe <nombre> <Identidad secreta> <Genero> <Procedencia>");
						}
					}
				}
			}

			// Comando para salir de la consola
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("Consola cerrada correctamente.");
				run = false;
			}
			System.out.print("almacen > ");
		}
		input.close();
    }
    
    public static void printHelp() {
		String output = "=========| AYUDA |=========\n";
		output += "help - Lista de comandos\n";
		output += "add <producto|cliente|direccion|pedido|productos> CONTENIDO\n";
		output += "display <all|almacen|clientes|productos|pedidos>\n";
		output += "json - Crear archivo json\n";
		output += "load <path> - Carga el documento XML desde la ruta especificada\n";
		output += "save <path> - Guarda el documento XML en la ruta especificada\n";
		output += "exit\n";
		System.out.println(output);
	}
}
