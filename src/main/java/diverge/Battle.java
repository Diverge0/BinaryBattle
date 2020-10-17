package main.java.diverge;



public class Battle{
    private BinaryBattle plugin;
    private String operationMode;
    public Battle(BinaryBattle plugin, String operationMode){
        this.plugin = plugin;
        this.operationMode = operationMode;
    }

    public void setOperationMode(String operationMode){
        this.operationMode = operationMode;
    }

    private boolean isInit(){
        return operationMode.equals("init");
    }

    private boolean isStart(){
        return operationMode.equals("start");
    }


}
