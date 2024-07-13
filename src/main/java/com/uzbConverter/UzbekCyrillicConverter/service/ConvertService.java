package com.uzbConverter.UzbekCyrillicConverter.service;



import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uzbConverter.UzbekCyrillicConverter.api.modul.Alphabet;
import com.uzbConverter.UzbekCyrillicConverter.api.repositories.AlphabetRepository;

import jakarta.transaction.Transactional;



@Service
public class ConvertService {

    @Autowired
    private AlphabetRepository alphabetRepository;

    @Transactional
    public void addData(Alphabet alphabet){
        alphabetRepository.save(alphabet);
    }








    @Transactional
    public String convertLatCyr(String text){

        String convertedText = "";

        Alphabet letter = new Alphabet();
        
        
        for(int i = 0; i < text.length(); i++){
            if(!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))){
                convertedText += (""+text.charAt(i));
            }
            else{
                letter = alphabetRepository.findByLatin("" + text.charAt(i));
            

                if(text.charAt(i) == 'e'){
                    if(i!=0){
                        if(text.charAt(i-1)!= ' ')
                            convertedText += "е";
                        else{
                            convertedText += letter.getCyrillic();
                        }
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                else if(text.charAt(i) == 'E' && (i == 0 || text.charAt(i-1) == ' ')){
                    convertedText += "Э";
                }
                else if(text.charAt(i) == 'c'){
                    String doubleLetter = "";
                    doubleLetter += ""+text.charAt(i)+text.charAt(i+1);
                    letter = alphabetRepository.findByLatin(doubleLetter);
                    convertedText += letter.getCyrillic();
                    i++;
                }
                else if(text.charAt(i) == 'C'){
                    String doubleLetter = "";
                    doubleLetter += ""+text.charAt(i)+text.charAt(i+1);
                    letter = alphabetRepository.findByLatin(doubleLetter);
                    convertedText += letter.getCyrillic();
                    i++;
                }
                else if(text.charAt(i) == 's'){
                    if(text.length() == i+1){
                        convertedText += letter.getCyrillic();
                    }
                    else if(text.charAt(i+1) == 'h'){
                        String doubleLetter = "";
                        doubleLetter += "" + text.charAt(i) + text.charAt(i+1);
                        letter = alphabetRepository.findByLatin(doubleLetter);
                        convertedText += letter.getCyrillic();
                        i++;
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                else if(text.charAt(i) == 'S'){
                    if(text.length() == i+1){
                        convertedText += letter.getCyrillic();
                    }
                    else if(text.charAt(i+1) == 'h'||text.charAt(i+1) == 'H'){
                        String doubleLetter = "";
                        doubleLetter += "" + text.charAt(i) + text.charAt(i+1);
                        letter = alphabetRepository.findByLatin(doubleLetter);
                        convertedText += letter.getCyrillic();
                        i++;
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                else if(text.charAt(i) == 'o' || text.charAt(i) == 'O'){
                    if(text.length() == i+1){
                        convertedText += letter.getCyrillic();
                    }
                    else if(text.charAt(i+1) == '\''||text.charAt(i+1) == '`'){
                        String doubleLetter = "";
                        doubleLetter += "" + text.charAt(i) + text.charAt(i+1);
                        letter = alphabetRepository.findByLatin(doubleLetter);
                        convertedText += letter.getCyrillic();
                        i++;
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                else if(text.charAt(i) == 'g' || text.charAt(i) == 'G'){
                    if(text.length() == i+1){
                        convertedText += letter.getCyrillic();
                    }
                    else if(text.charAt(i+1) == '\''||text.charAt(i+1) == '`'){
                        String doubleLetter = "";
                        doubleLetter += "" + text.charAt(i) + text.charAt(i+1);
                        letter = alphabetRepository.findByLatin(doubleLetter);
                        convertedText += letter.getCyrillic();
                        i++;
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                else if(text.charAt(i) == 'y' || text.charAt(i) == 'Y'){
                    if(text.length() == i+1){
                        convertedText += letter.getCyrillic();
                    }
                    else if(text.charAt(i+1) == 'a'|| text.charAt(i+1) == 'A' || text.charAt(i+1) == 'u' || text.charAt(i+1) == 'U' || text.charAt(i+1) == 'e' || text.charAt(i+1) == 'E'){
                        String doubleLetter = "";
                        doubleLetter += "" + text.charAt(i) + text.charAt(i+1);
                        letter = alphabetRepository.findByLatin(doubleLetter);
                        convertedText += letter.getCyrillic();
                        i++;
                    }
                    else{
                        convertedText += letter.getCyrillic();
                    }
                }
                
                else{
                    convertedText += letter.getCyrillic();
                }
            }
        }

        return convertedText;
    }








    @Transactional
    public String convertCyrLat(String text){

        String convertedText = "";

        Alphabet letter = new Alphabet();
        List<Alphabet> letters = new ArrayList<>();
        

        for(int i = 0; i < text.length(); i++){

            if(!(text.charAt(i) >= '\u0400' && text.charAt(i) <= '\u04FF')){
                convertedText += (""+text.charAt(i));
            }
            else{
                if(text.charAt(i) == 'Я'){
                    
                    if(i != 0){
                        convertedText += "YA";
                    }
                    else{
                        if(isCyrillicUppercase(text.charAt(i+1))){
                            convertedText += "YA";
                        }
                        else{
                            convertedText += "Ya";
                        }
                    }
                }
                else if(text.charAt(i) == 'Ю'){

                    if(i != 0){
                        convertedText += "YU";
                    }
                    else{
                        if(isCyrillicUppercase(text.charAt(i+1))){
                            convertedText += "YU";
                        }
                        else{
                            convertedText += "Yu";
                        }
                    }
                }
                else if(text.charAt(i) == 'Ш'){
                    if(i != 0){
                        convertedText += "SH";
                    }
                    else{
                        if(isCyrillicUppercase(text.charAt(i+1))){
                            convertedText += "SH";
                        }
                        else{
                            convertedText += "Sh";
                        }
                    }
                }
                else if(text.charAt(i) == 'Ч'){
                    if(i != 0){
                        convertedText += "CH";
                    }
                    else{
                        if(isCyrillicUppercase(text.charAt(i+1))){
                            convertedText += "CH";
                        }
                        else{
                            convertedText += "Ch";
                        }
                    }
                }
                else if(text.charAt(i) == 'е'){
                    if(i == 0){
                        convertedText += "ye";
                    }
                    else if(text.charAt(i-1) == ' '){
                        convertedText += "ye";
                    }
                    else{
                        convertedText += "e";
                    }
                }
                else if(text.charAt(i) == 'Е'){
                    if(i == 0 && isCyrillicUppercase(text.charAt(i+1))){
                        convertedText += "YE";
                    }
                    else if(text.charAt(i-1) == ' ' && isCyrillicUppercase(text.charAt(i+1))){
                        convertedText += "YE";
                    }
                    else if(i == 0 && !(isCyrillicUppercase(text.charAt(i+1)))){
                        convertedText += "Ye";
                    }
                    else if(text.charAt(i-1) == ' ' && !(isCyrillicUppercase(text.charAt(i+1)))){
                        convertedText += "Ye";
                    }
                    else{
                        convertedText += "E";
                    }
                }
                else if(text.charAt(i) == 'Ё'){
                    if(i != 0){
                        
                        convertedText += "YO";
                    }
                    else{
                        if(isCyrillicUppercase(text.charAt(i+1))){
                            
                            convertedText += "YO";
                        }
                        else{
                            letter = alphabetRepository.findByCyrillic(""+text.charAt(i));
                            convertedText += letter.getLatin();
                        }
                    }
                }
                else if(text.charAt(i) == 'ў' || text.charAt(i) == 'Ў' || text.charAt(i) == 'ғ' || text.charAt(i) == 'Ғ'){
                    letters = alphabetRepository.findAllByCyrillic("" + text.charAt(i));
                    letter = letters.get(0);
                    convertedText += letter.getLatin();
                }
                else if(text.charAt(i) == 'Э'){
                    convertedText += "E";
                }
                else if(text.charAt(i) == 'ь'){
                    continue;
                }
                else{
                    letter = alphabetRepository.findByCyrillic("" + text.charAt(i));
                    convertedText += letter.getLatin();
                }
            }
            
            
        }

        return convertedText;

    }

    public boolean isCyrillicUppercase(char c){
        return c >= '\u0400' && c <= '\u042F';
    }
    
}
