package Models;

import Utils.DataUtils;

public class BtnSelectMdl {
    public String getPL1UP()
    {
        return DataUtils.config.config.gameSettingsConfig.player1UpButton;
    }
    public String getPL1DOWN(){
        return DataUtils.config.config.gameSettingsConfig.player1DownButton;
    }
    public String getPL2UP()
    {
        return DataUtils.config.config.gameSettingsConfig.player2UpButton;
    }
    public String getPL2DOWN()
    {
        return DataUtils.config.config.gameSettingsConfig.player2DownButton;
    }

    public void setPlayersButtons(String p1u,String p1d,String p2u,String p2d) {
        DataUtils.config.config.gameSettingsConfig.player1UpButton = p1u;
        DataUtils.config.config.gameSettingsConfig.player1DownButton = p1d;
        DataUtils.config.config.gameSettingsConfig.player2UpButton = p2u;
        DataUtils.config.config.gameSettingsConfig.player2DownButton = p2d;
    }


    public boolean btnToChange (String p1u,String p1d,String p2u,String p2d){
        if (p1u.isEmpty() || p1d.isEmpty() || p2u.isEmpty() || p2d.isEmpty()) {
            return false;
        }
        else
            if (p1u.length() > 1 || p1d.length() > 1 || p2u.length() > 1 || p2d.length() > 1) {
                return false;
            }
            else
                if (p1u.equals(p1d) || p1u.equals(p2u) || p1u.equals(p2d) || p1d.equals(p2u) || p1d.equals(p2d) || p2u.equals(p2d)) {
                    return false;
                }
        else {
                setPlayersButtons(p1u,p1d,p2u,p2d);
                return true;
            }
    }


}
