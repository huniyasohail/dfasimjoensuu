/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Fabian
 */
public class HelpFileLoader {
    
    private HashMap<String,URL> helpfiles = new HashMap<String, URL>();

    public HelpFileLoader()
    {
        loadFiles();
    }

    public boolean loadFiles()
    {
        boolean readOk = true;
        loadFile("handtool");
        loadFile("addstatetool");
        loadFile("addtransitiontool");
        loadFile("simulation");
        return readOk;
    }

    private void loadFile(String s)
    {
        URL d = this.getClass().getResource("help/"+s+".html");
        helpfiles.put(s, d);
    }

    public URL getUrlByKey(String k)
    {
        return helpfiles.get(k);
    }




}
