package com.example.dashboard;

import java.util.*;

public class Processor {

    private int totalCustomers = 0;

    private List<int[]> offerCount = new ArrayList<>();

    public int getTotalCustomer() {
        return totalCustomers;
    }

    public void setTotalCustomer(int totalCustomer) {
        this.totalCustomers = totalCustomer;
    }

    public List<int[]> getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(List<int[]> offerCount) {
        this.offerCount = offerCount;
    }

    public List<int[]> offerPositions(Dictionary<String[], Integer> combination){
        Dictionary<String[], Integer> sortedKey = new Hashtable<>();
        List<int[]> offersPosition = new ArrayList<>();
        int size = combination.size();
        int curr = 0;
        for(Enumeration enm = combination.keys(); enm.hasMoreElements();)
        {
            String[] offersCombo = (String[]) enm.nextElement();
            int[] offerCount = {curr, offersCombo.length};
            offersPosition.add(offerCount);
            curr++;
        }
        return offersPosition;
    }

    public List<int[]> sortOffers(List<int[]> list){
        int[] max = list.get(0);
        int pos = 0;
        int maxPosition = 0;
        boolean maxFound = false;
        int index=0;
        while(true){
            if (pos== list.size())
                break;
            int[] temp = list.get(index);
            if (temp[1]>max[1]){
                max = temp;
                maxPosition = index;
                maxFound = true;
            }
            index++;
            if (index == list.size() && maxFound){
                list.remove(maxPosition);
                list.add(pos, max);
                pos++;
                if (pos== list.size())
                    break;
                max = list.get(pos);
                index = pos+1;
                maxFound = false;
            }
            else if (index == list.size() && !maxFound){
                pos++;
                if (pos== list.size())
                    break;
                max = list.get(pos);
                index = pos+1;
            }
            if (pos+1==list.size())
                break;;
        }
        return list;
    }

    public List<String> offerCombos(Dictionary<String[], Integer> combinations, List<int[]> list, Ingest_Overlap info){
        List<String> offers = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        setOfferCount(list);
        for(Enumeration enm = combinations.keys(); enm.hasMoreElements();)
        {
            String[] codes = (String[]) enm.nextElement();
            temp.add(decodeOffCode(codes, info));
        }

        for (int parse = list.size()-1; parse >-1; parse--){
            int[] offer = list.get(parse);
            offers.add(0,temp.get(offer[0]));
        }
        return offers;
    }

    public List<Integer> customerCount(Dictionary<String[], Integer> combinations, List<int[]> list){
        List<Integer> temp = new ArrayList<>();
        List<Integer> customerCount = new ArrayList<>();
        int totalCustomerCount = 0;
        for(Enumeration enm = combinations.elements(); enm.hasMoreElements();)
        {
            int customer = (Integer) enm.nextElement();
            temp.add(customer);
            totalCustomerCount = totalCustomerCount + customer;
        }
        setTotalCustomer(totalCustomerCount);
        for (int parse = list.size()-1; parse >-1; parse--){
            int[] offer = list.get(parse);
            customerCount.add(0,temp.get(offer[0]));
        }
        return customerCount;
    }

    public String decodeOffCode(String[] offerCodes, Ingest_Overlap offerDetails){
        String store = "";
        String[] codes = offerDetails.getOffers().get("Codes");
        String[] names = offerDetails.getOffers().get("Names");
        for (int index=0; index<offerCodes.length; index++){
            for (int parse=0; index<codes.length; parse++){
                if (codes[parse].matches(offerCodes[index])){
                    if (parse>0)
                        store = names[parse] + "; " + store  ;
                    else
                        store = store + " " + names[parse];
                    break;
                }
            }
        }
        return store;
    }

 //   public static void main(String[] args) {
//        Overlapfinder.Ingest files = new Overlapfinder.Ingest();
//        files.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad project\\Overlapfinder.Overlap finder\\files\\Offer_Combinations.xlsx");
//        files.parseSheets();
//        List<int[]> position = offerPositions(files.getCombination());
//        List<int[]> sort = sortOffers(position);
        //Dictionary<String[], Integer> overlap = offerConcentration(files.getCombination(), sort);
 //   }
}
