/**
 * 
 */
    Kakao.init('338d5b0fcaa6b7b36455221b4a956aa2');
    function chatChannel() {
      Kakao.Channel.chat({
        channelPublicId: '_tIzpT' // 카카오톡 채널 홈 URL에 명시된 id로 설정합니다.
      });
    }