package graphBFS;

import java.util.Scanner;

public class app {
	
	private static Scanner s;

	public static void main(String args[]) {
		graph g = new graph(15);
		g.addEdge(0, "Singapore", 1, "Hong Kong");
		g.addEdge(0, "Singapore", 4, "ShangHai");
		g.addEdge(0, "Singapore", 11, "Dubai");
		g.addEdge(0, "Singapore", 12, "Seoul");
		g.addEdge(0, "Singapore", 8, "Sydney");
		g.addEdge(1,"Hong Kong", 9, "Tokyo");
		g.addEdge(1,"Hong Kong", 2, "Toronto");
		g.addEdge(1,"Hong Kong", 3, "New York");
		g.addEdge(2,"Toronto", 9, "Tokyo");
		g.addEdge(2,"Toronto", 3, "New York");
		g.addEdge(3,"New York", 8, "Sydney");
		g.addEdge(3,"New York", 12, "Seoul");
		g.addEdge(4,"ShangHai", 5, "Stockholm");
		g.addEdge(4,"ShangHai", 9, "Tokyo");
		g.addEdge(5, "Stockholm", 8, "Sydney");
		g.addEdge(6, "London", 10, "Amsterdam");
		g.addEdge(6, "London", 11, "Dubai");
		g.addEdge(6, "London", 3, "New York");
		g.addEdge(7, "Kyoto", 4, "ShangHai");
		g.addEdge(7, "Kyoto", 9, "Tokyo");
		g.addEdge(7, "Kyoto", 8, "Sydney");
		g.addEdge(7, "Kyoto", 12, "Seoul");
		g.addEdge(10, "Amsterdam", 13, "Zurich");
		g.addEdge(10, "Amsterdam", 11, "Dubai");
		g.addEdge(11, "Dubai", 13, "Zurich");
		g.addEdge(11, "Dubai", 14, "Venice");
		g.addEdge(14, "Venice", 6, "London");
		
		
		
		System.out.println("Here is the list of source and destination cities:");
		System.out.print(
				"0:Singapore\n" + 
				"1:Hong Kong\n" +
				"2:Toronto\n" +
				"3:New York\n" +
				"4:ShangHai\n" +
				"5:Stockholm\n" +
				"6:London\n" +
				"7:Kyoto\n" +
				"8:Sydney\n" +
				"9:Tokyo\n" +
				"10:Amsterdam\n" +
				"11:Dubai\n" +
				"12:Seoul\n" +
				"13:Zurich\n" +
				"14:Venice\n" +
				"15: Show CPU times for graphs of different sizes(100, 1000, 2000) and minimum number of non-stop flights\n"+
				"16: Show CPU times for graphs of different sizes(100, 1000, 2000) and maximum number of non-stop flights\n" +
				"17: Show CPU times for graphs of different sizes(100, 1000, 2000) and intermediate(50%) number of non-stop flights");

		System.out.println ("\nPlease enter the number of source and destination cities (e.g. 4 5):\nor press 15/16/17 to find timing \n");

		s = new Scanner(System.in);
		int source = s.nextInt();
		
		if (source == 15) { // Each vertex is only connected to one vertex
			graph g1 = new graph(100);
			graph g2 = new graph(1000);
			graph g3 = new graph(2000);
			
			int numOfNonStopFlight = 0;

			//n = 100:    99 Non-Stop Flights, since there is no self-loop, takes atleast two nodes to form an edge
			for (int i = 0; i<99; i++) {
				g1.addEdge(i,i+1);	
				numOfNonStopFlight++;
			}
			
			long sysTime = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k)); //i to k
					g1.BFS(new Node(k), new Node(i));	//k to i
					bfs_count1 += 2; //+2 because 2 way
				}
			}

			long cpuTime = System.nanoTime() - sysTime; //total time for BFS of all nonStopFlight 
			System.out.println("Average CPU time for n = 100: "+ cpuTime/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			//n = 1000:   999 Non-Stop Flights
			numOfNonStopFlight = 0;
			for (int i = 0; i<999; i++) {
				g2.addEdge(i,i+1);	
				numOfNonStopFlight++;
			}
			
			long sysTime2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));	
					bfs_count2 += 2;
				}
			}
		
			long cpuTime2 = System.nanoTime()- sysTime2;
			System.out.println("Average CPU time for n = 1000: "+ cpuTime2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			//n = 2000:  1999 Non-Stop Flights
			numOfNonStopFlight = 0;
			for (int i = 0; i<1999; i++) {
				g3.addEdge(i,i+1);	
				numOfNonStopFlight++;
				}
			long sysTime3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));	// Back and forth
					bfs_count3 += 2;
					}
				}
			long cpuTime3 = System.nanoTime() - sysTime3;
			System.out.println("Average CPU time for n = 2000: "+ cpuTime3/bfs_count3 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
		}
		else if (source == 16) {
			
			graph g1 = new graph(100);
			graph g2 = new graph(1000);
			graph g3 = new graph(2000);
			
			//n = 100:   Number of Non-Stop Flights = 99 + 98 + 97 +... 1, complete graph has n(n-1)/2 edges
			int numOfNonStopFlight = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			System.out.println("the total number of non-stop flight is" + numOfNonStopFlight);
			
			long sysTime = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k));
					g1.BFS(new Node(k), new Node(i));	
					bfs_count1 += 2;
				}
			}
			long cpuTime = System.nanoTime() - sysTime; // time taken for all bfs
			System.out.println("Average CPU time for n = 100: "+ cpuTime/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);

			//n = 1000:  Number of Non-Stop Flights = 999 + 998 + 997 +... 1
			numOfNonStopFlight = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.addEdge(i,k);	
					numOfNonStopFlight++;
				}
			}
			long sysTime2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));
					bfs_count2 += 2;
					}
				}
			long cpuTime2 = System.nanoTime()- sysTime2;
			System.out.println("Average CPU time for n = 1000: "+ cpuTime2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			
			//n = 2000:      Number of Non-Stop Flights = 1999 + 1998 + 1997 +... 1
			numOfNonStopFlight=0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.addEdge(i,k);	
					numOfNonStopFlight++;
				}
			}

			long sysTime3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k = i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));
					bfs_count3 = bfs_count3+2;
				}
			}
			long cpuTime3 = System.nanoTime()- sysTime3;
			System.out.println("Average CPU time for n = 2000: "+ cpuTime3/bfs_count3 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			return;
			
		}
		
		
else if (source == 17) {
			
			graph g1 = new graph(100);
			graph g2 = new graph(1000);
			graph g3 = new graph(2000);
			
			//n = 100:
			int numOfNonStopFlight =0;
			for (int i = 0; i<50; i++) {
				for (int k =i+1; k<100; k++) {
					g1.addEdge(i,k);	
					numOfNonStopFlight++;
				}
			}
			System.out.println("the total number of non-stop flight is" + numOfNonStopFlight);
			long sysTime = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k));
					g1.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count1 += 2;
					}
				}
			long cpuTime = System.nanoTime() - sysTime;
			System.out.println("Average CPU time for n = 100: "+ cpuTime/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			//n = 1000:
			numOfNonStopFlight= 0;
			for (int i = 0; i<500; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.addEdge(i,k);	
					numOfNonStopFlight++;
				}
			}
			
			long sysTime2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));
					bfs_count2 += 2;
				}
			}
			long cpuTime2= System.nanoTime()- sysTime2;
			System.out.println("Average CPU time for n = 1000: "+ cpuTime2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+numOfNonStopFlight);
			
			//n = 2000:
			numOfNonStopFlight =0 ;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			long sysTime3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count3 += 2;
					}
				}
			long cpuTime3= System.nanoTime()- sysTime3;
			System.out.println("Average CPU Time for Graph Size 2000: "+ cpuTime3/bfs_count3 + "ns"); 
			System.out.println("Number of Non-Stop Flights = "+numOfNonStopFlight);
			return;
}

		int destination = s.nextInt();
		
		String source_string = null;
		switch (source) {
		
		case 0:
			source_string = "Singapore";
			break;
		case 1:
			source_string = "Hong Kong";
			break;
		case 2:
			source_string = "Toronto";
			break;
		case 3:
			source_string = "New York";
			break;
		case 4:
			source_string = "ShangHai";
			break;
		case 5:
			source_string = "Stockholm";
			break;
		case 6:
			source_string = "London";
			break;
		case 7:
			source_string = "Kyoto";
			break;
		case 8:
			source_string = "Sydney";
			break;
		case 9:
			source_string = "Tokyo";
			break;
		case 10:
			source_string = "Amsterdam";
			break;
		case 11:
			source_string = "Dubai";
			break;
		case 12:
			source_string = "Seoul";
			break;
		case 13:
			source_string = "Zurich";
			break;
		case 14:
			source_string = "Venice";
			break;
		default:
			System.out.println("Please input 1 to 14");
			break;
		}
		
		System.out.println("The shortest flight path: ");
		System.out.println("From: ");
		System.out.println(source_string);
		g.BFS(new Node(source), new Node(destination));
	}

	private static void printf(String string) {
		// TODO Auto-generated method stub
		
	}
}

