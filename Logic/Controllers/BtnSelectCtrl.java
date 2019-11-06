package Controllers;

import Models.BtnSelectMdl;

public class BtnSelectCtrl {
    private BtnSelectMdl btnSelectMdl = new BtnSelectMdl();

    public String GetP1U(){
      return btnSelectMdl.getPL1UP();
    }
    public String GetP1D(){
        return btnSelectMdl.getPL1DOWN();
    }
    public String GetP2U(){
        return btnSelectMdl.getPL2UP();
    }
    public String GetP2D(){
        return btnSelectMdl.getPL2DOWN();
    }

    public boolean changesToStore (String p1u,String p1d,String p2u,String p2d){
        if(!btnSelectMdl.btnToChange(p1u,p1d,p2u,p2d))
            return false;
        return true;
    }


}
