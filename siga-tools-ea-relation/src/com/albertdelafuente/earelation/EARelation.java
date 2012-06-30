/*
 * To change this template, choose Tools | Templates
 * and open the template in the editorepo.
 */
package com.albertdelafuente.earelation;
import org.sparx.*;
import java.lang.*;
import java.util.*;
import static java.lang.System.out;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;

/**
 *
 * @author afu
 */
public class EARelation {
    private String pRelationsF, pEapF, pSourceNS, pDestinationNS;
    private String[][] relMatrix;
    private Map<String, org.sparx.Element> sMap;
    private Map<String, org.sparx.Element> dMap;
    private org.sparx.Repository repo;
    
    EARelation() {
        pEapF = "";
        repo = new org.sparx.Repository();
        sMap = new HashMap<String, org.sparx.Element>();
        dMap = new HashMap<String, org.sparx.Element>();
        out.println("constructor");
    }

    void openRepository() {
        if (repo.OpenFile(pEapF)) {
            out.println("b is true");
        }
    }

    void listRepositoryInfo() {
        out.println("-- Repostitory --");
        //out.println("Models Count: " + repo.GetModels().GetCount());
        //out.println("Diagrams Count: " + repo.GetModels().GetCount());
        out.println("Instance GUID: " + repo.GetInstanceGUID()); // Earepository.InstanceGUID
        out.println("ConnectionString: " + repo.GetConnectionString());
        out.println("Library version: " + repo.GetLibraryVersion());
        out.println("Model count: " + repo.GetModels().GetCount());
        out.println("Terms count: " +  repo.GetTerms().GetCount());
        out.println("Issues count: " + repo.GetIssues().GetCount());
        out.println("Author count: " + repo.GetAuthors().GetCount());
        out.println("Client count: " + repo.GetClients().GetCount());
        out.println("Task count: " + repo.GetTasks().GetCount());
        out.println("Datatypes count: " + repo.GetDatatypes().GetCount());
        out.println("Recource count: " + repo.GetResources().GetCount());
        out.println("Stereotype count: " + repo.GetStereotypes().GetCount());
        out.println("PropertyType count: " + repo.GetPropertyTypes().GetCount());
    }
    
    void listModels(int indentLevel, org.sparx.Repository EaRepository){
        short idx;
        org.sparx.Package i;
        String tab = new String(new char[indentLevel]).replace('\0', ' ');
        
        out.printf("%s-- Models [%d] (a package) --\n", tab, EaRepository.GetModels().GetCount());
        for(idx=0; idx<EaRepository.GetModels().GetCount(); idx++){
	   i = EaRepository.GetModels().GetAt(idx);
	   out.printf("%sModel #%d ---\n", tab, idx);
	   out.printf("%sName: %s, PackageID: %s, PackageGUID %s\n", tab, i.GetName(), i.GetPackageID(), i.GetPackageGUID());
	   out.printf("%sCreated: %s, Modified: %s, Version: %s\n", tab, i.GetCreated(), i.GetModified(), i.GetVersion());
	   out.printf("%sIsNamespace: %b, IsControlled: %b\n", tab, i.GetIsNamespace(), i.GetIsControlled());
	   out.printf("%sIsProtected %b, IsModel: %b\n", tab, i.GetIsProtected(), i.GetIsModel());
	   out.printf("%sUseDTD: %b, LogXML: %b, XMLPath: %s\n", tab, i.GetUseDTD(), i.GetLogXML(), i.GetXMLPath());
	   out.printf("%sLastLoadDate: %s, LastSaveDate: %s\n", tab, i.GetLastLoadDate(), i.GetLastSaveDate());
	   out.printf("%sOwner: %s, CodePath: %s\n", tab, i.GetOwner(), i.GetCodePath());
	   out.printf("%sUMLVersion: %s, TreePos: %s\n", tab, i.GetUMLVersion(), i.GetTreePos());
	   out.printf("%sElement: %s, IsVersionControlled: %s\n", tab, i.GetElement(), i.GetIsVersionControlled());
	   out.printf("%sBatchLoad: %d, BatchSave: %d\n", tab, i.GetBatchLoad(), i.GetBatchSave());
	   out.printf("%sNotes: %s\n", tab, i.GetNotes());
	   out.printf("%sPackage count: %d\n", tab, i.GetPackages().GetCount());
	   out.printf("%sElement count: %d\n", tab, i.GetElements().GetCount());
	   out.printf("%sDiagram count: %d\n", tab, i.GetDiagrams().GetCount());
	   out.printf("%sConnector count: %d\n", tab, i.GetConnectors().GetCount());
           out.printf("%s---\n", tab, idx);
        }
    }

    void listPackages(int indentLevel, org.sparx.Package root) {
        short idx;
        org.sparx.Package i;
        String tab = new String(new char[indentLevel]).replace('\0', ' ');
        
        out.printf("%s-- Packages [%d] --\n", tab, root.GetPackages().GetCount());
        for (idx=0; idx<root.GetPackages().GetCount(); idx++) {
   	   i = root.GetPackages().GetAt(idx);
	   out.printf("%sPackage #%d\n", tab, idx);
           out.printf("%sPackage id: %d\n", tab, i.GetPackageID());
	   out.printf("%sName: %s\n", tab, i.GetName());
        }
    }
    
    void listDiagrams(int indentLevel, org.sparx.Package root) {
        short idx;
        org.sparx.Diagram i;
        String tab = new String(new char[indentLevel]).replace('\0', ' ');
        
        out.printf("%s-- Diagrams [%d] --\n", tab, root.GetDiagrams().GetCount());
        for (idx=0; idx<root.GetDiagrams().GetCount(); idx++) {
   	   i = root.GetDiagrams().GetAt(idx);
	   out.printf("%sDiagram #%d\n", tab, idx);
           out.printf("%sGUID id: %s\n", tab, i.GetDiagramGUID());
	   out.printf("%sName: %s\n", tab, i.GetName());
        }
    }
    
    void listElements(int indentLevel, org.sparx.Package root) {
        short idx;
        org.sparx.Element i;
        String tab = new String(new char[indentLevel]).replace('\0', ' ');
        
        out.printf("%s-- Diagrams [%d] --\n", tab, root.GetElements().GetCount());
        for (idx=0; idx<root.GetElements().GetCount(); idx++) {
   	   i = root.GetElements().GetAt(idx);
	   out.printf("%sElement #%d\n", tab, idx);
           out.printf("%sGUID id: %s\n", tab, i.GetElementGUID());
	   out.printf("%sName: %s\n", tab, i.GetName());
        }
    }

    void listSelected(org.sparx.Package root) {
        short i;
        org.sparx.Element e;

        for (i=0; i<root.GetElements().GetCount(); i++) {
   	   e = root.GetElements().GetAt(i);
	   out.printf("Element #%d\n", i);
           //out.printf("ID: %d\n", x.GetPackageID());
	   out.printf("Name: %s\n", e.GetName());   
        }
    }
    
    String subAlias(String s){
        String alias = s.substring(0, Math.min(s.length(), 7));
        return alias;
    }
    
    void relatewfrfi() {
        short i, j;
        org.sparx.Package reqp, wfp, root;
        org.sparx.Element req, wf;
        
        out.println("-- Packages  --");
        //listSubPackages(repo.GetPackageByID(0));
        reqp = repo.GetModels().GetByName("SIGA stable");
        reqp = reqp.GetPackages().GetByName("Biblioteca de Requisitos (RFI / RFN / RNF / RN)");
        reqp = reqp.GetPackages().GetByName("Requisitos Funcionais de Interface (RFI)");
        reqp = reqp.GetPackages().GetByName("Comum - Requisitos Funcionais de Interface (RFI)");
        //listPackages(4, reqp);
        //listDiagrams(6, reqp);
        //listElements(4, reqp);
        
        wfp = repo.GetModels().GetByName("SIGA stable");
        wfp = wfp.GetPackages().GetByName("Biblioteca de Interfaces");
        wfp = wfp.GetPackages().GetByName("test");
        //listElements(6, wfp);
        
        for (i=0; i<wfp.GetElements().GetCount(); i++) {
   	    wf = wfp.GetElements().GetAt(i);
            for (j=0; j<reqp.GetElements().GetCount(); j++) {
                req = reqp.GetElements().GetAt(j);

                out.printf("wf(%d) = %s\n", i, wf.GetName());
                out.printf("wf(%d) = %s\n", i, subAlias(wf.GetName()));
                out.printf("req(%d) = %s\n", j, req.GetName());
                out.printf("req(%d) = %s\n", j, subAlias(req.GetName()));
                
                if (subAlias(req.GetAlias()).equals(subAlias(wf.GetAlias()))) {
                    out.printf("Creating relation between:\n");
                    out.printf("  Element #%s\n", wf.GetElementGUID());
                    out.printf("  Element #%s\n", req.GetElementGUID());
//                    wf.GetConnectors().AddNew(pEapF, pEapF);
                    
                    //Connector addNew = wf.GetConnectors().AddNew("name", "UseCase"),
                    Connector addNew = wf.GetConnectors().AddNew(subAlias(req.GetAlias()), "Realization");
                    addNew.SetSupplierID(req.GetElementID());
                    addNew.Update();
                    wf.Refresh();

                    
//                    Connector addNew = e1.GetConnectors().AddNew("moj usecase",  "UseCase");
//                    addNew.SetSupplierID(e2.GetElementID());
//                    addNew.Update();
//                    e1.Refresh();
//                    
//                    EA.Connector newConnector = Element.Connectors.AddNew("""Realization");
//                    newConnector.SupplierID = myTargetID;
//                    newConnector.Update(); 
                }
            }
        }

    }
    
    void loadRelationships() throws IOException {
        short i, j;
        org.sparx.Package reqp, wfp, root;
        org.sparx.Element req, wf;
        

            CSVParser parser = new CSVParser(new FileReader(pRelationsF), CSVStrategy.TDF_STRATEGY);
            
            // Load the relationship matrix
            relMatrix = parser.getAllValues();
            //String[] namespaces = parser.getLine();
            
//            out.printf("rm[0][0] = %s\n", relMatrix[0][0]);
//            out.printf("rm[1][1] = %s\n", relMatrix[1][1]);
//            out.printf("rm[1][8] = %s\n", relMatrix[1][8]);
//            out.printf("rm[8][1] = %s\n", relMatrix[8][1]);
    }
    
    String getSourceNS(){
        pSourceNS = relMatrix[0][0];
        return pSourceNS;
    }

    String getDestinationNS(){
        pDestinationNS = relMatrix[0][1];
        return pDestinationNS;
    }
    
    boolean relExists(String a, String b){
        short i;
        boolean result;
        
        out.printf("relMatrix.length = %d\n", relMatrix.length);

        i = 0;
        result = false;
        while (i < relMatrix.length && !result) {
            out.printf("testing... %s vs. %s\n", relMatrix[i][0].substring(0, 6), a.substring(0, 6));
            out.printf("testing... %s vs. %s\n", relMatrix[i][1].substring(0, 6), b.substring(0, 6));
            result = a.substring(0, 6).equals(relMatrix[i][0].substring(0, 6)) &&
                     b.substring(0, 6).equals(relMatrix[i][1].substring(0, 6));
            i++;
        }
        return result;
    }
    
    int nsTokenCount(String ns){
        
        // Example:
        // SIGA stable|Biblioteca REQ..|Requisitos FI|Comum
        
        StringTokenizer st = new StringTokenizer(ns, "|");
        return st.countTokens();
    }
    
    String nsTokenGet(String ns, int n){
        int i = 0;
        
        // Example:
        // SIGA stable|Biblioteca REQ..|Requisitos FI|Comum
        
        StringTokenizer st = new StringTokenizer(ns, "|");
        while(st.hasMoreTokens() && i < n){
            st.nextToken();
            //out.printf("nsGet(%d) = %s\n", i, st.nextToken());
            i++;
        }
        return st.nextToken();
    }
    
    org.sparx.Package nsPackageFetch(String ns, Map<String, org.sparx.Element> m){
        short i;
        org.sparx.Package p;
        org.sparx.Element e;
        
        // Example:
        // SIGA stable|Biblioteca REQ..|Requisitos FI|Comum
        
        p = repo.GetModels().GetByName(nsTokenGet(ns, 0));
        out.printf("opening... %s\n", nsTokenGet(ns, 0));
        for (i = 1; i < nsTokenCount(ns); i++) {
            p = p.GetPackages().GetByName(nsTokenGet(ns, i));
            out.printf("opening... %s\n", nsTokenGet(ns, i));
        }
        
        for (i = 0; i < p.GetElements().GetCount(); i++) {
            e = p.GetElements().GetAt(i);
            m.put(e.GetAlias().substring(0, 6), e);
            out.printf("mapping... [%s] -> [%s]\n", e.GetAlias().substring(0, 6), e.GetName());
        }
        
        return p;
    }
    
    void relate() throws IOException {
        short i, j;
        org.sparx.Package sPackage, dPackage;
        org.sparx.Element sElement, dElement;
       
        sPackage = nsPackageFetch(pSourceNS, sMap);
        dPackage = nsPackageFetch(pDestinationNS, dMap);
        
        for (i = 0; i<sPackage.GetElements().GetCount(); i++) {
   	    sElement = sPackage.GetElements().GetAt(i);
            for (j=0; j<dPackage.GetElements().GetCount(); j++) {
                dElement = dPackage.GetElements().GetAt(j);

                out.printf("s(%d) = %s\n", i, sElement.GetName());
                out.printf("s(%d) = %s\n", i, subAlias(sElement.GetName()));
                out.printf("d(%d) = %s\n", j, dElement.GetName());
                out.printf("d(%d) = %s\n", j, subAlias(dElement.GetName()));
                
                if (subAlias(sElement.GetAlias()).equals(subAlias(dElement.GetAlias()))) {
                    out.printf("Creating relation between:\n");
                    out.printf("  Element #%s\n", sElement.GetElementGUID());
                    out.printf("  Element #%s\n", dElement.GetElementGUID());
//                    wf.GetConnectors().AddNew(pEapF, pEapF);
                    
                    //Connector addNew = wf.GetConnectors().AddNew("name", "UseCase"),
                    Connector addNew = sElement.GetConnectors().AddNew(subAlias(dElement.GetAlias()), "Realization");
                    addNew.SetSupplierID(dElement.GetElementID());
                    addNew.Update();
                    sElement.Refresh();
                }
            }
        }
    }
    
    void relatecsv() throws IOException {
        short i;
        String relname;
        org.sparx.Package sPackage, dPackage;
        org.sparx.Element sElement, dElement;
        Connector addNew;
       
        sPackage = nsPackageFetch(pSourceNS, sMap);
        dPackage = nsPackageFetch(pDestinationNS, dMap);

        for (i = 1; i < relMatrix.length; i++) {
            sElement = sMap.get(relMatrix[i][0].substring(0, 6));
            dElement = dMap.get(relMatrix[i][1].substring(0, 6));
            //out.printf("testing... %s vs. %s\n", relMatrix[i][0].substring(0, 6));
            //out.printf("testing... %s vs. %s\n", relMatrix[i][1].substring(0, 6));
            if (sElement == null){
                out.printf("SKIPPING (not found): %s on namespace: %s\n", relMatrix[i][0].substring(0, 6), pSourceNS);
            } else {
                out.printf("Source element found... %s\n", sElement.GetName());
                if (dElement == null){
                    out.printf("SKIPPING (not found): %s on namespace: %s\n", relMatrix[i][1].substring(0, 6), pDestinationNS);
                } else {
                    relname = relMatrix[i][2];
                    if (relMatrix[i][2].compareToIgnoreCase("auto") == 0 || 
                        relMatrix[i][2].equals("")) {
                        relname = "rel-" + relMatrix[i][0].substring(0, 6) + "-" +
                            relMatrix[i][1].substring(0, 6);
                    }
                    out.printf("Destination element found... %s\n", dElement.GetName());
                    out.printf("Doing the magic tricks: creating relation [%s] between [%s] and [%s]\n",
                        relname, sElement.GetName(), dElement.GetName());
                    addNew = sElement.GetConnectors().AddNew(relname, "Realization");
                    addNew.SetSupplierID(dElement.GetElementID());
                    addNew.Update();
                    sElement.Refresh();
                }
            }
        }

//        for (i=0; i<sPackage.GetElements().GetCount(); i++) {
//            for (j=0; j<dPackage.GetElements().GetCount(); j++) {
//                dElement = dPackage.GetElements().GetAt(j);
//
//                out.printf("s(%d) = %s\n", i, sElement.GetName());
//                out.printf("s(%d) = %s\n", i, subAlias(sElement.GetName()));
//                out.printf("d(%d) = %s\n", j, dElement.GetName());
//                out.printf("d(%d) = %s\n", j, subAlias(dElement.GetName()));
//                
//                if (subAlias(sElement.GetAlias()).equals(subAlias(dElement.GetAlias()))) {
//                    out.printf("Creating relation between:\n");
//                    out.printf("  Element #%s\n", sElement.GetElementGUID());
//                    out.printf("  Element #%s\n", dElement.GetElementGUID());
////                    wf.GetConnectors().AddNew(pEapF, pEapF);
//                    
//                    //Connector addNew = wf.GetConnectors().AddNew("name", "UseCase"),
//                    Connector addNew = sElement.GetConnectors().AddNew(subAlias(dElement.GetAlias()), "Realization");
//                    addNew.SetSupplierID(dElement.GetElementID());
//                    addNew.Update();
//                    sElement.Refresh();
//                }
//            }
//        }
    }
    
    public void run (String[] args) throws Exception {
        // Parameters example
        // earelation -e file.eap -m matrix.csv -r /root/model/rowns -c /root/model/colns -l file -v 3
        // earelation -eap file.eap -l -option(-all -guid) /root/model/submodel -o file -v 3
        // $0 -i eap -o file
        
        pEapF = "C:\\siga-tools-ea-relation\\siga.eap";
        pRelationsF = "C:\\siga-tools-ea-relation\\relation-list.csv";
        
        try {
            loadRelationships();
            openRepository();
            //listRepositoryInfo();
            //listModels(2, repo);
            //nsPackageFetch("SIGA stable|Biblioteca de Requisitos (RFI / RFN / RNF / RN)|Requisitos Funcionais de Interface (RFI)|Comum - Requisitos Funcionais de Interface (RFI)");
            //nsPackageFetch("SIGA stable|Biblioteca de Interfaces|test", sMap);
            out.printf("getSourceNS() = %s\n", getSourceNS());
            out.printf("getDestinationNS() = %s\n", getDestinationNS());
            relExists("RFI001.", "RFI001.");
            relatecsv();
        } catch(Exception e) {
        } finally {
        }
        //listSubPackages(4, repo.GetModels().GetAt((short) 0));
        //listSubPackages(4, repo.GetModels().GetByName("SIGA stable"));
        //listSubPackages(6, repo.GetModels().GetAt((short) 0).GetPackages().GetAt((short) 1));
        //listSubPackages(6, repo.GetModels().GetAt((short) 0).GetPackages().GetAt((short) 1).GetPackages().GetAt((short) 1));
        //listSubPackages(repo.getp);
        //relatewfrfi();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            EARelation eai = new EARelation();
            eai.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}