const inputText = document.getElementById("inputText");
const outputText = document.getElementById("outputText");
const inputSelect = document.getElementById("inputLang");
const outputSelect = document.getElementById("outputLang");

function setOutputLang(){
    if(inputSelect.value === "Uzbek"){
        outputSelect.value = "Кирил";
    }
    else{
        outputSelect.value = "Uzbek";
    }
}

function convert(){
    $("#convertButton").prop("disabled", true);
    if(inputSelect.value === "Uzbek"){
        $.ajax({
            type: 'POST',
            url: '/convertLatCyr',
            data: {receivedText: inputText.value},
            success: function(data){
                outputText.value = data;
            },
            complete: function(){
                $("#convertButton").prop("disabled", false);
            }
        })
    }
    else{
        $.ajax({
            type: 'POST',
            url: '/convertCyrLat',
            data: {receivedText: inputText.value},
            success: function(data){
                outputText.value = data;
            },
            complete: function(){
                $("#convertButton").prop("disabled", false);
            }
        })
    }
}