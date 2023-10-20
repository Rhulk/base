var player;
function onYouTubePlayerAPIReady() {
    player = new YT.Player('idframe', {
      height: '100%',
      width: '100%',
      videoId: video,
      events: {
        'onReady': onAutoPlay,
        'onStateChange': onFinish
      }
    });
}
function onAutoPlay(event) {
    event.target.playVideo();
}
function onFinish(event) {        
    if(event.data === 0) {            
        alert("Fin");
    }

}