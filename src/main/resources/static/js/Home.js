
const start = () => {
    console.log("kkkk")
    if ($(".sidebar").is(":visible")){
         $(".bar").css("display","none")
     }
     else{

    }
}

const toggle = () => {
    // console.log($(".sidebar").is(":visible"))
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display","none")
        $(".sidebar").css("transition","0.3")
        $(".sidebar").prop("disabled","true")
        $(".bar").css("display","block")
        $(".bar").css("padding-left","2px")
        $(".dashboard_banner").css("padding-left","2px")
    }
    else{
        $(".sidebar").css("display","block")
    }
}

const toggleBar = () => {
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display","none")
        $(".sidebar").css("transition","0.3")
        $(".bar").prop("disabled",true)
    }
    else{
        $(".sidebar").css("display","block")
        $(".bar").css("display","none")
        $(".dashboard_banner").css("padding-left","9%")
    }
}