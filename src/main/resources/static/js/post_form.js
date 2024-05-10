document.getElementById("postForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    var file = (document.getElementById("dataImg")).files[0];
    var reader = new FileReader();
    reader.onload = function(event) {
        const base64String = event.target.result.split(',')[1];
        console.log(base64String);
        let formData = new FormData(document.getElementById("postForm"));
        formData.append("imgBase64",base64String);
        fetch("/admin", {
            method: "POST",
            body: formData
        }).then(response => {
            // Обработка ответа, если необходимо
            console.log("POST запрос выполнен успешно");
        }).catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
    };
    reader.readAsDataURL(file);
});