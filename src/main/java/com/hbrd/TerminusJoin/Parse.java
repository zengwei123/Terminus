package com.hbrd.TerminusJoin;

import com.hbrd.Model.Message;
import com.hbrd.Service.impl.CarImpl;
import com.hbrd.Util.ApplicationContextProvider;
import com.hbrd.Util.Util;
import com.hbrd.Util.Verify;

public class Parse {
    private static StringBuffer  basis=new StringBuffer ();
    private static StringBuffer  warning=new StringBuffer ();
    private static StringBuffer  bms=new StringBuffer ();
    private static Message message=new Message();
    /**
     * 字节转字符
     */
    public static Message bytesToHexString(byte[] bArray) {
        int jiaoyanm=0;
        StringBuffer sb = new StringBuffer(300);
        if((0xFF & bArray[0])==35&&(0xFF & bArray[1])==35&& bArray[0xFF &(int)Util.GetTcpData(bArray,25,2)+28]==36){
            for (int i = 2; i < bArray.length; i++) {
                if(i<=(0xFF &Util.GetTcpData(bArray,25,2)+26)){
                    int c=(0xFF & bArray[i]);
                    jiaoyanm=jiaoyanm^(0xFF & bArray[i]);
                    /**实时信息**/
                    if((0xFF & bArray[2])==2||(0xFF & bArray[2])==3){
                        /**车辆上下线判断**/
                        if(i==2){
                            sb.append(c+",");
                        }
                        /**车辆序列号**/
                        if(i==4||i==5||i==6||i==7||i==8||i==9||i==10||i==11||i==12||i==13){
                            sb.append((char) c);
                            if(i==13){
                                sb.append(",");
                            }
                        }
                        /**时间**/
                        if(i==27||i==28||i==29||i==30||i==31||i==32){
                            if(i==27){
                                sb.append("20"+c);
                            }else{
                                if(c<10){
                                    sb.append("0"+c);
                                }else{
                                    sb.append(c);
                                }
                                if(i==32||i==29){
                                    sb.append(",");
                                }
                            }
                        }
                        int sum=(int)Util.GetTcpData(bArray,77,2);
                        switch (i){
                            case 34: sb.append(c+",");break;
                            case 35: sb.append(c+",");break;
                            case 36: sb.append(c+",");break;
                            case 37: sb.append(Util.GetTcpData(bArray,37,2)+",");break;
                            case 39: sb.append(Util.GetTcpData(bArray,39,4)+",");break;
                            case 43: sb.append(Util.GetTcpData(bArray,43,2)+",");break;
                            case 45: sb.append(Util.GetTcpData(bArray,45,2)+",");break;
                            case 47: sb.append(c+",");break;
                            case 48: sb.append(c+",");break;
                            case 49: sb.append(c+",");break;
                            case 50: sb.append(c+",");break;
                            case 51: sb.append(c+",");break;
                            case 52: sb.append(Util.GetTcpData(bArray,52,2)+",");break;
                            case 54: sb.append(Util.GetTcpData(bArray,54,2)+",");break;
                            case 56: sb.append(c+",");break;
                            case 57: sb.append(c+",");break;
                            case 58: sb.append(c+",");break;
                            case 59: sb.append(c+",");break;
                            case 60: sb.append(c+",");break;
                            case 61: sb.append(Util.GetTcpData(bArray,61,2)+",");break;
                            case 63: sb.append(Util.GetTcpData(bArray,63,2)+",");break;
                            case 65: sb.append(c+",");break;
                            case 66: sb.append(Util.GetTcpData(bArray,66,2)+",");break;
                            case 68: sb.append(Util.GetTcpData(bArray,68,2)+",");break;
                            case 70: sb.append(c+",");break;
                            case 71: sb.append(Util.GetTcpData(bArray,71,2)+",");break;
                            case 73: sb.append(Util.GetTcpData(bArray,73,2)+",");break;
                            case 75: sb.append(Util.GetTcpData(bArray,75,2)+",");break;
                            case 77:
                                for(int z=1;z<=sum;z++){
                                    sb.append((0xFF&bArray[78+z])+",");
                                }
                                break;
                        }
                        if(80+sum==i)
                            sb.append(Util.GetTcpData(bArray,80+sum,2)+",");
                        if(82+sum==i)
                            sb.append(c+",");
                        if(83+sum==i)
                            sb.append(Util.GetTcpData(bArray,83+sum,2)+",");
                        if(85+sum==i)
                            sb.append(c+",");
                        if(86+sum==i)
                            sb.append(Util.GetTcpData(bArray,86+sum,2)+",");
                        if(88+sum==i||89+sum==i||90+sum==i||91+sum==i)
                            sb.append(c+",");
                        if(92+sum==i)
                            sb.append(Util.GetTcpData(bArray,92+sum,2)+",");
                        if(94+sum==i)
                            sb.append(Util.GetTcpData(bArray,94+sum,2)+",");
                        if(96+sum==i||97+sum==i)
                            sb.append(c+",");
                        if(98+sum==i)
                            sb.append(Util.GetTcpData(bArray,98+sum,4)+",");
                        if(102+sum==i)
                            sb.append(Util.GetTcpData(bArray,102+sum,4)+",");
                        if(106+sum==i||107+sum==i||108+sum==i){
                            sb.append(c+",");
                        }
                        if(109+sum==i)
                            sb.append(Util.GetTcpData(bArray,109+sum,2)+",");
                        if(111+sum==i||112+sum==i)
                            sb.append(c+",");
                        if(113+sum==i)
                            sb.append(Util.GetTcpData(bArray,113+sum,2)+",");
                        if(115+sum==i||116+sum==i||117+sum==i||118+sum==i||119+sum==i||120+sum==i||121+sum==i||122+sum==i)
                            sb.append(c+",");
                        if(123+sum==i)
                            sb.append(Util.GetTcpData(bArray,123+sum,4)+",");
                        if(127+sum==i)
                            sb.append(c+",");
                        if(128+sum==i)
                            sb.append(Util.GetTcpData(bArray,128+sum,4)+",");
                        if(132+sum==i)
                            sb.append(Util.GetTcpData(bArray,132+sum,4)+",");
                        if(136+sum==i)
                            sb.append(c+",");
                        if(137+sum==i)
                            sb.append(Util.GetTcpData(bArray,137+sum,4)+",");
                        if(141+sum==i)
                            sb.append(Util.GetTcpData(bArray,141+sum,4)+",");
                        if(145+sum==i)
                            sb.append(c+",");
                        if(146+sum==i)
                            sb.append(Util.GetTcpData(bArray,146+sum,4)+",");
                        if(150+sum==i)
                            sb.append(c);
                    }


                    /**上线**/
                    if((0xFF & bArray[2])==1){
                        /**时ICCID获取  只有在上线的时候才能够拼接获取**/
                        if(i==35){
                            for(int z=35;z<=55;z++){
                                c=(0xFF & bArray[z]);
                                sb.append((char)c);
                                if(z==55){
                                    sb.append(",");
                                }
                            }
                        }
                    }

                }else{
                    /**校验操作**/
                    if(jiaoyanm!=(0xFF &bArray[0xFF &(int)Util.GetTcpData(bArray,25,2)+27])){
                        System .out.println((0xFF &bArray[0xFF &(int)Util.GetTcpData(bArray,25,2)+27])+"-"+jiaoyanm);
                        break;
                    }else{
                        System .out.println((0xFF &bArray[0xFF &(int)Util.GetTcpData(bArray,25,2)+27])+"-"+jiaoyanm);
                        break;
                    }
                }
            }

        }else{
            return null;
        }
        System.out.println(sb.toString());
        String[] hbrd=sb.toString().split(",");
        message.setTime(hbrd[2]);
        message.setDate(hbrd[2]+""+hbrd[3]);
        message.setCarId(hbrd[1]);
        message.setCarBasis(Basis(hbrd));
        message.setCarWarning(Warning(hbrd,message));
        message.setCarBms(Bms(hbrd));
        message.setCarX(Util.getxy(hbrd[45],hbrd[46])[0]+"");
        message.setCarY(Util.getxy(hbrd[45],hbrd[46])[1]+"");
        if(hbrd[0].equals("1")||hbrd[1].equals("4")){
            message.setIsStatus(Integer.parseInt(hbrd[0]));
        }else{
            message.setIsStatus(1);
            message.setIsReal(Integer.parseInt(hbrd[0]));
        }
        message.setIsWarning(0);
        return  message;
    }

    public static String Basis(String[] strings){
        basis.setLength(0);
        basis.append(strings[4]+",");  //车辆状态
        basis.append(strings[5]+",");  //充电状态
        basis.append(strings[6]+",");  //运行模式
        basis.append(strings[7]+",");  //车速
        basis.append(strings[8]+",");  //里程
        basis.append(strings[9]+",");  //总电压
        basis.append(strings[10]+","); //总电流
        basis.append(strings[11]+",");  //soc
        basis.append(strings[12]+",");  //DCDC
        basis.append(strings[13]+",");  //挡位
        basis.append(strings[14]+",");  //加速踏板行程
        basis.append(strings[15]+",");  //制动踏板
        basis.append(strings[16]);  //绝缘电阻
        return basis.toString();
    }
    public static String Warning(String[] strings,Message message){
        warning.setLength(0);
        warning.append(strings[39]+",");  //高压DC/DC状态
        warning.append(strings[41]+",");  //发动机状态
        warning.append(strings[42]+",");  //发动机转速
        warning.append(strings[43]+",");  //燃料消耗率
        warning.append(strings[49]+",");  //最高电压电池子系统号
        warning.append(strings[50]+",");  //最高电压电池单体代号
        warning.append(strings[51]+",");  //电池单体电压最高值
        warning.append(strings[52]+",");  //最低电压电池子系统号
        warning.append(strings[53]+",");  //最低电压电池单体代号
        warning.append(strings[54]+","); //电池单体电压最低值
        warning.append(strings[55]+","); //最高温度子系统
        warning.append(strings[56]+","); //最高温度探针序
        warning.append(strings[58]+","); //最低温度子系统
        warning.append(strings[59]); //最低温度探针序号
        if(strings[39].equals("254")||strings[41].equals("254")||strings[42].equals("254")||strings[43].equals("254")||strings[49].equals("254")||strings[50].equals("254")||strings[51].equals("254")
                ||strings[52].equals("254")||strings[53].equals("254")||strings[54].equals("254")||strings[55].equals("254")||strings[56].equals("254")||strings[58].equals("254")||strings[59].equals("254")){
            message.setIsWarning(1);
        }
        return warning.toString();
    }
    public static String Bms(String[] strings){
        bms.setLength(0);
        bms.append(strings[20]+",");  //驱动电机状态
        bms.append(strings[21]+",");  //驱动电机N控制器温度
        bms.append(strings[22]+",");  //驱动电机N转速
        bms.append(strings[23]+",");  //驱动电机N转矩
        bms.append(strings[24]+",");  //驱动电机N温度
        bms.append(strings[25]+",");  //电机控制器N输入电压
        bms.append(strings[26]+",");  //电机控制器N直流母线电流
        bms.append(strings[27]+",");  //燃料电池数据
        bms.append(strings[28]+",");  //燃料电池电压
        bms.append(strings[29]+",");  //燃料电池电流
        bms.append(strings[30]+",");  //燃料电池消耗率
        bms.append(strings[31]+",");  //燃料电池消耗率
        bms.append(strings[33]+","); //氢系统中最高温度
        bms.append(strings[35]+","); //氢气最高浓度
        bms.append(strings[37]);//氢气最高压力
        return bms.toString();
    }
}
