package main;
import m1graf2021.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String NO_GRAF_ERROR_MESSAGE = "You must first create or load a graph.\n";

    Graf currentGraf = null;
    boolean quit = false;
    Scanner scanner = new Scanner(System.in);

    public void init() {

    }

    public void run() {
        while(!quit) {
            mainMenu();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.run();
    }

    public Integer getUserInput() {
        Integer input = null;
        String inputString = scanner.next();
        try{
            input = Integer.valueOf(inputString);
        }
        catch (NumberFormatException e) {

        }

        return input;
    }

    public void mainMenu() {
        printMainMenu();
        handleMainMenuUserInput();
    }

    public void printMainMenu() {
        String menu = "Select an action (enter the corresponding number) :\n\n" +
                "0. Quit\n" +
                "1. Create a graph\n" +
                "2. Load a graph\n" +
                "3. Print graph on console\n" +
                "4. Manage nodes\n" +
                "5. Manage edges\n" +
                "6. Computations/operations on the graph\n" +
                "7. Save graph to dot file\n";

        System.out.println(menu);
    }

    public void handleMainMenuUserInput() {
        Integer input = getUserInput();
        while (input == null) {
            input = getUserInput();
        }

        switch (input) {
            case 0:
                this.quit = true;
                break;
            case 1:
                createGraphMenu();
                break;
            case 2:
                loadGraphMenu();
                break;
            case 3:
                if(currentGraf != null) System.out.println(currentGraf.toDotString());
                break;
            case 4:
                manageNodeMenu();
                break;
            case 5:
                manageEdgeMenu();
                break;
            case 6:
                operationsMenu();
                break;
            case 7:
                saveGraphMenu();
                break;
            default:
                break;
        }
    }

    public void createGraphMenu() {
        // TODO provide the possibility to the user to choose between directed and undirected graph
        printCreateGraphMenu();
        handleCreateGraphMenu();
    }

    public void printCreateGraphMenu() {
        String menu = "Create a graph :\n\n" +
                "0. Go back\n" +
                "1. Create an empty graph\n" +
                "2. Create a graph\n";

        System.out.println(menu);
    }

    public void handleCreateGraphMenu() {
        Integer input = getUserInput();
        while (input == null) {
            input = getUserInput();
        }

        switch (input) {
            case 0:
                break;
            case 1:
                this.currentGraf = new Graf();
                break;
            case 2:
                createGraphByEnterringSuccessorArray();
                break;
            default:
                break;
        }
    }

    public void createGraphByEnterringSuccessorArray() {
        System.out.println("\nEnter the successor array of the graf in the following format : nodeId,...,0,nodeId,...,0,....,0 : ");

        boolean validInput = false;
        String inputLine;
        do{
            inputLine = scanner.next();
            inputLine.trim();
            inputLine.replaceAll("\\s+", "");

            String regex = "^(\\d+,)*0$";
            if(!inputLine.matches(regex))
                System.out.println("The input is in wrong format.");
            else
                validInput = true;

        }while (!validInput);

        String inputSplitted[] = inputLine.split(",");
        int inputs[] = Arrays.stream(inputSplitted).mapToInt(Integer::parseInt).toArray();
        this.currentGraf = new Graf(inputs);
    }

    public void loadGraphMenu() {
        printLoadGraphMenu();
        handleLoadGraphMenu();
    }

    public void printLoadGraphMenu() {
        String menu = "Load a graph :\n\n" +
                "0. Go back\n" +
                "1. Load graph from dot file\n";

        System.out.println(menu);
    }

    public void handleLoadGraphMenu() {
        Integer input = getUserInput();
        while(input == null) {
            input = getUserInput();
        }

        switch (input) {
            case 0:
                break;
            case 1:
                System.out.println("Enter file path : \n");
                Graf graf = null;
                while(graf == null) {
                    String path = this.scanner.nextLine();
                    graf = Graf.fromDotFile(path);
                    if(path == null) System.out.println("Loading of " + path + " failed");
                    else this.currentGraf = graf;
                }
                break;
        }
    }

    public void saveGraphMenu() {
        printSaveGraphMenu();
        handleSaveGraphMenu();
    }

    public void printSaveGraphMenu() {
        String menu = "Save the current graph to a file :\n";
        System.out.println(menu);
    }

    public void handleSaveGraphMenu() {
        if(this.currentGraf == null) System.out.println(this.NO_GRAF_ERROR_MESSAGE);
        else {
            System.out.println("Enter the path :\n");
            String path = this.scanner.nextLine();
            this.currentGraf.toDotFile(path);
        }
    }

    public void manageNodeMenu() {
        printManageNodeMenu();
        handleManageNodeMenu();
    }

    public void printManageNodeMenu() {
        if(this.currentGraf == null) {
            System.out.println(this.NO_GRAF_ERROR_MESSAGE);
        }
        else {
            String menu = "Manage nodes :\n\n" +
                    "0. Go to previous menu\n" +
                    "1. Add a node\n" +
                    "2. Remove a node\n";

            System.out.println(menu);
        }
    }

    public void handleManageNodeMenu() {
        Integer input = getUserInput();
        while(input == null) {
            input = getUserInput();
        }

        switch (input) {
            case 0:
                break;
            case 1:
                System.out.println("Enter node id :\n");
                Integer nodeId = getUserInput();
                while(nodeId != null) {
                    nodeId = getUserInput();
                    if(currentGraf.existsNode(nodeId)) {
                        System.out.println("The node" + nodeId + "already exists. Please enter another id :\n");
                        nodeId = null;
                    }
                }

                currentGraf.addNode(nodeId);
                manageNodeMenu();
                break;
            case 2:
                System.out.println("Enter node id :\n");
                Integer removeNodeId = getUserInput();
                while(removeNodeId != null) {
                    removeNodeId = getUserInput();
                }

                if(!currentGraf.existsNode(removeNodeId)) {
                    System.out.println("The node" + removeNodeId + "does not exists.\n");
                    removeNodeId = null;
                }
                else{
                    currentGraf.removeNode(removeNodeId);
                }
                manageNodeMenu();
                break;
            default:
                break;
        }
    }

    public void manageEdgeMenu() {
        printManageEdgeMenu();
        handleManageEdgeMenu();
    }

    public void printManageEdgeMenu() {
        if(this.currentGraf == null) {
            System.out.println(this.NO_GRAF_ERROR_MESSAGE);
        }
        else {
            String menu = "Manage nodes :\n\n" +
                    "0. Go to previous menu\n" +
                    "1. Add an edge\n" +
                    "2. Remove an edge\n";

            System.out.println(menu);
        }
    }

    public void handleManageEdgeMenu() {
        Integer input = getUserInput();
        while(input == null) {
            input = getUserInput();
        }

        switch (input) {
            case 0:
                break;
            case 1:
                System.out.println("Enter from node id :\n");
                Integer fromNodeId = getUserInput();
                while(fromNodeId != null) {
                    fromNodeId = getUserInput();
                    if(fromNodeId.equals(-1)){
                        manageEdgeMenu();
                        return;
                    }
                    if(fromNodeId <= 0 || !currentGraf.existsNode(fromNodeId)) {
                        System.out.println("The node" + fromNodeId + "does not exists. Please enter another id or cancel (-1) :\n");
                        fromNodeId = null;
                    }
                }

                Integer toNodeId = getUserInput();
                while(toNodeId != null) {
                    toNodeId = getUserInput();
                    if(toNodeId.equals(-1)){
                        manageEdgeMenu();
                        return;
                    }
                    if(toNodeId <= 0 || !currentGraf.existsNode(toNodeId)) {
                        System.out.println("The node" + toNodeId + "does not exists. Please enter another id or cancel (-1) :\n");
                        toNodeId = null;
                    }
                }

                boolean hasWeight = false;
                System.out.println("Add weight ? (Yes | No)\n");
                if(scanner.nextLine().toLowerCase().equals("Yes")) hasWeight = true;

                System.out.println("Enter weight :\n");
                Integer weight = getUserInput();
                while (weight == null){
                    weight = getUserInput();
                }

                if(hasWeight) currentGraf.addEdge(fromNodeId, toNodeId, weight);
                else currentGraf.addEdge(fromNodeId, toNodeId);
                manageEdgeMenu();
                break;
            case 2:
                System.out.println("Enter from node id :\n");
                Integer removeFromNodeId = getUserInput();
                while(removeFromNodeId != null) {
                    removeFromNodeId = getUserInput();
                    if(removeFromNodeId.equals(-1)){
                        manageEdgeMenu();
                        return;
                    }
                    if(removeFromNodeId <= 0 || !currentGraf.existsNode(removeFromNodeId)) {
                        System.out.println("The node" + removeFromNodeId + "does not exists. Please enter another id or cancel (-1) :\n");
                        removeFromNodeId = null;
                    }
                }

                Integer removeToNodeId = getUserInput();
                while(removeToNodeId != null) {
                    removeToNodeId = getUserInput();
                    if(removeToNodeId.equals(-1)){
                        manageEdgeMenu();
                        return;
                    }
                    if(removeToNodeId <= 0 || !currentGraf.existsNode(removeToNodeId)) {
                        System.out.println("The node" + removeToNodeId + "does not exists. Please enter another id or cancel (-1) :\n");
                        removeToNodeId = null;
                    }
                }

                if(currentGraf.existsEdge(removeFromNodeId, removeToNodeId))
                    currentGraf.removeEdge(removeFromNodeId, removeToNodeId);
                manageEdgeMenu();
                break;
            default:
                break;
        }
    }

    public void operationsMenu() {
        printOperationsMenu();
        handleOperationsMenu();
    }

    public void printOperationsMenu() {
        String menu = "Computations / operations :\n\n" +
                "0. Go back\n" +
                "1. Print reverse graph\n" +
                "2. Print transitive closure\n" +
                "3. Print BFS walk\n" +
                "4. Print DFS walk\n";
    }

    public void handleOperationsMenu() {
        Integer input = getUserInput();
        while (input == null) {
            input = getUserInput();
        }

        if(this.currentGraf == null){
            System.out.println(this.NO_GRAF_ERROR_MESSAGE);
            return;
        }

        switch (input) {
            case 0:
                break;
            case 1:
                System.out.println(this.currentGraf.getReverse().toDotString());
                break;
            case 2:
                System.out.println(this.currentGraf.getTransitiveClosure().toDotString());
                break;
            case 3:
                List<Node> bfsWalk = this.currentGraf.getBFS();
                if(bfsWalk != null) {
                    String bfsWalkString = "";
                    for(int i=0; i< bfsWalk.size(); i++) {
                        bfsWalkString += (i == 0 ? "" : ", ") + bfsWalk.get(i).getId();
                    }
                    System.out.println(bfsWalkString);
                }
                break;
            case 4:
                List<Node> dfsWalk = this.currentGraf.getDFS();
                if(dfsWalk != null) {
                    String dfsWalkString = "";
                    for(int i=0; i< dfsWalk.size(); i++) {
                        dfsWalkString += (i == 0 ? "" : ", ") + dfsWalk.get(i).getId();
                    }
                    System.out.println(dfsWalkString);
                }
                break;
            default:
                break;
        }
    }
}
