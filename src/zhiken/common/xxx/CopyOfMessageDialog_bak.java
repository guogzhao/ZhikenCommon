package zhiken.common.xxx;
//package zhiken.common.widget;
//
//import android.app.Activity;
//import android.app.AlertDialog.Builder;
//import android.content.DialogInterface;
//import android.view.Gravity;
//import android.widget.Toast;
//
///** 
// *  
// * ͨ����ͨʽ Message Dialog ��Ϣ�Ի�����ʾ�� 
// * ������ʾͨ�õ���ͨʽ������Ϣ�Ի���  
// *  
// * @author Michael 
// * 
// */  
//public class CopyOfMessageDialog_bak {  
//  
//  
//    private String sCaptionClose="�ر�";  
//    private String sCaptionOk="ȷ��";  
//    private String sCaptionCancel="ȡ��";  
//    private int iIcoResourceId=-1;  
//      
//  
//    private Activity activity=null;  
//      
//    public String getCaptionClose() {  
//        return sCaptionClose;  
//    }  
//    public void setCaptionClose(String sCaptionClose) {  
//        this.sCaptionClose = sCaptionClose;  
//    }  
//  
//    public String getCaptionOk() {  
//        return sCaptionOk;  
//    }  
//    public void setCaptionOk(String sCaptionOk) {  
//        this.sCaptionOk = sCaptionOk;  
//    }  
//  
//    public String getCaptionCancel() {  
//        return sCaptionCancel;  
//    }  
//    public void setCaptionCancel(String sCaptionCancel) {  
//        this.sCaptionCancel = sCaptionCancel;  
//    }     
//      
//    public int getIcoResourceId() {  
//        return iIcoResourceId;  
//    }  
//    public void setIcoResourceId(int iIcoResourceId) {  
//        this.iIcoResourceId = iIcoResourceId;  
//    }  
//      
//      
//      
////-----------------------------------------------------------------------     
//      
//    public CopyOfMessageDialog_bak(Activity activity) {  
//        this.activity=activity;  
//    }  
//      
////-----------------------------------------------------------------------     
//      
//    /** 
//     * ��ʾ���б������ʾ��Ϣ�Ի��� 
//     * �öԻ��������һ�����رա���ť 
//     *  
//     * @param requestCode           ����ID�� 
//     * @param title                 ��Ϣ��ʾ���� 
//     * @param message               ��Ϣ��ʾ���� 
//     * @param listener              �Ի��򰴼�����¼��������� 
//     */  
//    public void ShowInfo(int requestCode,String title,String message,IMessageDialogListener listener){  
//          
//        Builder builder = CreateDialogBuilder(title,message);  
//                  
//        if(listener!=null){  
//            builder.setPositiveButton(sCaptionClose ,  
//                    new DialogOnClickListener(requestCode, 0, listener)  
//                    );  
//        }else{  
//            builder.setPositiveButton(sCaptionClose ,null);  
//        }  
//        builder.create().show();          
//    }  
//      
//    /** 
//     * ��ʾ���б������ʾ��Ϣ�Ի��� 
//     * �öԻ��������һ�����رա���ť 
//     * Ĭ�ϼ���ID��Ϊ888888 
//     *  
//     * @param title                 ��Ϣ��ʾ���� 
//     * @param messager              ��Ϣ��ʾ���� 
//     */  
//    public void ShowInfo(String title,String messager){  
//        ShowInfo(888888,title,messager,null);  
//    }  
//      
//    /** 
//     * ��ʾ�����������ʾ��Ϣ�Ի��� 
//     * �öԻ��������һ�����رա���ť 
//     * Ĭ�ϼ���ID��Ϊ888888 
//     *  
//     * @param messager              ��Ϣ��ʾ���� 
//     */   
//    public void ShowInfo(String messager){  
//        ShowInfo(888888,"",messager,null);  
//    }  
//      
//      
//    /** 
//     * ��ʾ���б����ȷ����Ϣ�Ի��� 
//     * �öԻ�����С�ȷ�ϡ��͡�ȡ����������ť 
//     *  
//     * @param requestCode           ����ID�� 
//     * @param title                 ��Ϣ��ʾ���� 
//     * @param message               ��Ϣ��ʾ���� 
//     * @param listener              �Ի��򰴼�����¼��������� 
//     */  
//    public void ShowConfirm(int requestCode,String title,String message,IMessageDialogListener listener){  
//          
//        Builder builder = CreateDialogBuilder(title,message);  
//          
//        if(listener!=null){  
//            builder.setPositiveButton(sCaptionOk ,  
//                    new DialogOnClickListener(requestCode, 1, listener)  
//                    );  
//            builder.setNegativeButton(sCaptionCancel ,  
//                    new DialogOnClickListener(requestCode, 2, listener)  
//                    );  
//        }else{  
//            builder.setPositiveButton(sCaptionOk ,null);  
//            builder.setPositiveButton(sCaptionCancel ,null);  
//        }  
//        builder.create().show();          
//    }     
//      
//  
////----------------------------------------------------------------------------------------------------------------    
//      
//  
//    /** 
//     * �����Ի���Android��AlertDialog.Builder���� 
//     *  
//     * @param title                 ��Ϣ��ʾ���� 
//     * @param message               ��Ϣ��ʾ���� 
//     * @return                  AlertDialog.Builder���� 
//     */  
//    public android.app.AlertDialog.Builder CreateDialogBuilder(String title,String message){  
//          
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);  
//          
//        builder.setTitle(title);  
//        builder.setMessage(message);          
//        if(this.iIcoResourceId!=-1){  
//            builder.setIcon(this.iIcoResourceId);  
//        }  
//          
//        return builder;  
//    }     
//      
////----------------------------------------------------------------------------------------------------------------    
//      
//  
//  
//    /** 
//     * ��Toast�ķ�ʽ��ʾ��ʾ��Ϣ 
//     * ��Toast�����κΰ�ť 
//     *  
//     * @param message           ��ʾ��Ϣ 
//     */  
//    public void ShowToast(String message){  
//          
//        Toast t =null;  
//      
//        t = Toast.makeText(this.activity, message, Toast.LENGTH_LONG);  
//        t.setGravity(Gravity.CENTER, 0, 0);  
//        t.show();     
//    }  
////----------------------------------------------------------------------------------------------------------------    
//      
//    private class DialogOnClickListener implements DialogInterface.OnClickListener{  
//  
//        private int requestCode;  
//        private int clickid=0;  
//        private IMessageDialogListener listener;  
//          
//        public DialogOnClickListener(int requestCode,int clickid,IMessageDialogListener listener){  
//            this.requestCode=requestCode;  
//            this.clickid=clickid;  
//            this.listener=listener;       
//        }  
//          
//        @Override  
//        public void onClick(DialogInterface dialog, int which) {  
//            // TODO Auto-generated method stub  
//            if(this.listener!=null){  
//                switch(this.clickid){  
//                    case 0:  
//                        this.listener.onDialogClickClose(this.requestCode);  
//                        break;  
//                    case 1:  
//                        this.listener.onDialogClickOk(this.requestCode);  
//                        break;  
//                    case 2:  
//                        this.listener.onDialogClickCancel(this.requestCode);  
//                        break;  
//                }             
//            }  
//              
//        }  
//  
//    }//end class      
//      
////----------------------------------------------------------------------------------------------------------------    
//      
//}//end class  
