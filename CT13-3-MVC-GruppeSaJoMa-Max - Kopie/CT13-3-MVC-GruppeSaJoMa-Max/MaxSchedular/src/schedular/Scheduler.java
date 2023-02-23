package schedular;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import javax.security.auth.x500.X500Principal;
import javax.swing.JOptionPane;


public class Scheduler {
	
	InputModel m = new InputModel();
	OutputModel o = new OutputModel(m);
	View v = new View();
	
	ProcessPrioComparator prioComparator = new ProcessPrioComparator();
	
	String[] pName = new String[m.getRowCount()];
	String[] pTime = new String[m.getRowCount()];
	int[] pPrio = new int[m.getRowCount()];
	ArrayList<Process> processes = m.getData();
	ArrayList<ArrayList<String>> processesOutput;
	private Process cacheProcess;
	ArrayList<Integer> timesList;
	ArrayList<String> waitTime;
	private boolean run = false;
	
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	private final static String newline = "\n";
	
	public String[] getName() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pName[i] = (String)m.getValueAt(0, i);
		}
		return pName;
	}
	
	public String[] getTime() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pTime[i] = (String)m.getValueAt(1, i);
		}
		return pTime;
	}
	
	public int[] getPrio() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pPrio[i] = (Integer)m.getValueAt(2, i);
		}
		return pPrio;
	}
	
	public Process getCacheProcess() {
		return cacheProcess;
	}

	public void startScheduling() {
		v.getTextArea().setText("");
		this.run = true;
		processes = m.getData();
		processesOutput = new ArrayList<>(processes.size());
		int processGetterEval = processes.size();
		int processGetter = 1;
		for(int k = 0; k < processes.size(); k++) {
			processesOutput.add(k, processes.get(k).tickStates);
			v.getTextAreaStateOutput().append((k + 1) + ". " + "Zustaende Prozess: " + processes.get(k).getpName());
			v.getTextAreaStateOutput().append(newline);
		}
		v.getTextAreaStateOutput().append(newline);
		while(run) {
			Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
			cacheProcess = processes.get(0);
			printArrays();
			if(cacheProcess.getTimesList().isEmpty()) {
				simOutput(cacheProcess, 2);
				cacheProcess.setState(ProcessState.FINISHED);
				if(processes.size() > 1) {
					processes.remove(0);
				}
				else {
					for(int whichList = 0; whichList < processesOutput.size(); whichList++) {
						v.getTextAreaStateOutput().append(processGetter + " :  ");
						if(processGetter < processGetterEval) processGetter++;
						else processGetter = 1;
						for(int list = 0; list < processesOutput.get(whichList).size(); list++) {
							v.getTextAreaStateOutput().append(processesOutput.get(whichList).get(list) + "  |  ");
						}
						v.getTextAreaStateOutput().append(newline);
					}
					processesOutput.clear();
					JOptionPane.showMessageDialog(null, "Alle Prozesse sind fertig.");
					m.setDefaultData();
					this.run = false;
				}
			}
			else {
				if(cacheProcess.getIsCalc()) {
					cacheProcess.setState(ProcessState.CALC);
					cacheProcess.getTickStates().add("r");
					simOutput(cacheProcess, 0);
					if(cacheProcess.getTimesList().get(0) > 1) {
						cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 1);
					}
					else {
						if(cacheProcess.getpPrio() > 1) {
							cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
						}
						else {
							cacheProcess.setpPrio(0);
						}
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setState(ProcessState.BLOCKED);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
					}
				} 
				else {
					cacheProcess.setState(ProcessState.BLOCKED);
						simOutput(cacheProcess, 1);
						if(cacheProcess.getTimesList().get(0) > 1) {
							cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 1);
						}
						else {
							cacheProcess.getTimesList().remove(0);
							cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
							cacheProcess.setState(ProcessState.WAITING);
						}
				}
				for(int p = 1; p < processes.size(); p++) {
					if(!processes.get(p).getIsCalc()) {
						if(processes.get(p).getTimesList().isEmpty()) {
							simOutput(processes.get(p), 2);
							processes.get(p).setState(ProcessState.FINISHED);
							processes.remove(p);
						}
						else {
							simOutput(processes.get(p), 1);
							processes.get(p).getTickStates().add("b");
							if(processes.get(p).getTimesList().get(0) > 1) {
								processes.get(p).getTimesList().set(0, processes.get(p).getTimesList().get(0) - 1);
							}
							else {
								processes.get(p).getTimesList().remove(0);
								processes.get(p).setIsCalc(!processes.get(p).getIsCalc());
								processes.get(p).setState(ProcessState.WAITING);
							}
						}
					}
					else {
						simOutput(processes.get(p), 3);
						processes.get(p).getTickStates().add("w");
					}
				}
				v.getTextArea().append("-----//-----");
				v.getTextArea().append(newline);
			}
		}
		
	}
	
	public Scheduler(InputModel inputModel, OutputModel outputModel, View view) {
		m = inputModel;
		o = outputModel;
		v = view;
	}
	
	public void printProccessList(ArrayList<Process> pList) {
		for(Process process : pList) {
			System.out.println(process);
		}
	}
	
	private void simOutput(Process process, int output) {
		switch (output) {
		case 0:
			v.getTextArea().append(process.getpName() + " rechnet, noch " + process.getTimesList().get(0) + " ZE..." + newline);
			break;
		case 1:
			v.getTextArea().append(process.getpName() + " ist blockiert fuer noch " + process.getTimesList().get(0) + " ZE..." + newline);
			break;
		case 2:
			v.getTextArea().append(process.getpName() + " ist komplett fertig." + newline);
			break;
		case 3:
			v.getTextArea().append(process.getpName() + " wartet." + newline);
			break;
		default:
			break;
		}
		
	}
	
	private void printArrays() {
		System.out.println("time List" + cacheProcess.getpName() + " : " + cacheProcess.getTimesList());
		System.out.println("----------");
		System.out.println(processesOutput);
		System.out.println("-----//-----");
	}

}
