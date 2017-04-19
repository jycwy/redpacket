package redpacket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cwy
 */
public class RedPacket {
    private static final float MINMONEY = 0.01f;
    private static final float MAXMONEY = 200f;
    private static double TIMES = 2.1;
    
    public List<Float> splitRedPackets(float money,int count){
        if(!isRight(money,count)){
            return null;
        }
        List<Float> list = new ArrayList<Float>();
        float max = (float)(money*TIMES/count);
        max = max > MAXMONEY?MAXMONEY:max;
        
        for(int i =0; i < count; i++){
            float one = randomRedPacket(money,MINMONEY,max,count - i);
            list.add(one);
            money -= one;
        }       
        return list;
    }
    
    private boolean isRight(float money,int count){
        double avg = money / count;
        if(avg<MINMONEY){
            return false;
        }
        else if(avg>MAXMONEY){
            return false;
        }
        return true;
    }
    
    private float randomRedPacket(float money,float mins,float maxs,int count){
        if(count == 1){
            return (float)(Math.round(money*100))/100;
        }
        if(mins==maxs){
            return mins;  //???
        }
        float max = maxs > money? money:maxs;
        float one = ((float)Math.random() * (max - mins) + mins);  //??
        one = (float)(Math.round(one*100) /100);;
        float moneyOther = money - one;
        if(isRight(moneyOther,count-1)){
            return one;
        } else{
            float avg = moneyOther / (count-1);
            if(avg<MINMONEY)
            {
                return randomRedPacket(money,mins,one,count);
            }else if(avg>MAXMONEY)
            {
                return randomRedPacket(money,one,maxs,count);
            }
        }
        
        return one;
    }
    
    
    public static void main(String[] args) {
        RedPacket util = new RedPacket();
        System.out.println(util.splitRedPackets(200,100));
    }
    
}
