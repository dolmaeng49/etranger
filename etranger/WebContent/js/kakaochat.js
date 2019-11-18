/**
 * 
 */
    Kakao.init('6396a2950329721839be306c16858ef7');
    function chatChannel() {
      Kakao.Channel.chat({
        channelPublicId: '_tIzpT' // 카카오톡 채널 홈 URL에 명시된 id로 설정합니다.
      });
    }