const dataForm = document.getElementById("dataForm");

if(dataForm){
    dataForm.addEventListener('submit', function(event){
        event.preventDefault();

        const formData = new FormData(dataForm);

        $.ajax({
            type: 'POST',
            url: '/addData',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
                const parsedData = JSON.parse(data);

                

                const successMessage = document.createElement("h4");
                successMessage.textContent = "Added successfully: " + parsedData.latin + " " + parsedData.cyrillic;
                successMessage.style.color = 'green';
                document.body.appendChild(successMessage);
            }

        })
    })
}