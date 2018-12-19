function expandUpClick() {

    var up = $("#imageDiv").attr("scrollTop");
    $("#imageDiv").attr("scrollTop", up - 30);

}

function expandDownClick() {

    var down = $("#imageDiv").attr("scrollTop");
    $("#imageDiv").attr("scrollTop", down + 30);

}

function expandLeftClick() {

    var left = $("#imageDiv").attr("scrollLeft");
    $("#imageDiv").attr("scrollLeft", left - 30);

}

function expandRightClick() {

    var right = $("#imageDiv").attr("scrollLeft");
    $("#imageDiv").attr("scrollLeft", right + 30);

}

function zoominClick() {
    scale = scale * 1.2;
    var images1 = document.getElementById('img');
    var heightOri = GetImageHeight(images1);
    var widthOri = GetImageWidth(images1);
    images1.height = heightOri * scale;
    images1.width = widthOri * scale;
    //var width = $('#img').width();
    //var height = $('#img').height();
    //$('#img').css("width",width/1.2);
    //$('#img').css("height",width/1.2);

}
function zoomoutClick() {
    scale = scale / 1.2;
    var images1 = document.getElementById('img');
    var heightOri = GetImageHeight(images1);
    var widthOri = GetImageWidth(images1);
    images1.height = heightOri * scale;
    images1.width = widthOri * scale;
}

var OriginImage = new Image();
function GetImageWidth(oImage) {
    if (OriginImage.src != oImage.src)
        OriginImage.src = oImage.src;
    return OriginImage.width;
}

function GetImageHeight(oImage) {
    if (OriginImage.src != oImage.src)
        OriginImage.src = oImage.src;
    return OriginImage.height;
}

function realSizeClick() {
    scale = 1;
    var images1 = document.getElementById('img');
    var height1 = GetImageHeight(images1);
    var width1 = GetImageWidth(images1);
    images1.height = height1;
    images1.width = width1;
}

function defaultZoom() {
    var images1 = document.getElementById('img');
    var heightOri = GetImageHeight(images1);
    var widthOri = GetImageWidth(images1);
    images1.height = heightOri * scale;
    images1.width = widthOri * scale;
}

function rotate180Click() {
    $('#img').rotateRight(180);

    angleValue += 180;
    if (angleValue >= 360) {
        angleValue = angleValue - 360;
    }
}

function rotateRightClick() {
	


    $('#img').rotateRight(90);
    angleValue += 90;
    if (angleValue >= 360) {
        angleValue = angleValue - 360;
    }

	
}

function rotateLeftClick() {
    $('#img').rotateRight(-90);
    angleValue -= 90;
    if (angleValue <= -360) {
        angleValue = angleValue + 360;
    }
	
}