//= require jquery/dist/jquery
//= require bootstrap/dist/js
//= require angular/angular
//= require_tree views
//= require_self

$(function(){

    $("#loginForm").on("submit",function(submitEvent){
        submitEvent.preventDefault();
        var username = $("#username").val();
        var password = $("#password").val();
        submitForm(username,password);
    });

});

var submitForm= function submitForm(username,password){
    var redirectUrl="${createLink(action:'index' ,controller:'home')}";
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/project-management-application/api/login",
        contentType: "json",
        dataType: 'json',
        data:JSON.stringify({username:username,password:password}),
        statusCode: {
            401: function() {
               console.log("Authentication failed");
            }

        },
        success: function (response) {
            console.log(response);
            window.location.assign(redirectUrl);
        },
        error: function (xhr,status) {
            console.log(status);
        }

    });


};