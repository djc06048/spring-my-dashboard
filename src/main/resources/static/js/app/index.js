var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.postSave();
        });

        $('#btn-post-update').on('click', function () {
            _this.postUpdate();
        });
        $('#btn-comment-update').on('click', function () {
            _this.commentUpdate();
        });
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });
    },
    postSave : function () {
        var data = {
            title: $('#title').val(),
            userId: $('#author').val(), //사용자 일련번호
            content: $('#content').val()
        };
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts', //포스트 작성하는 api로 명령보내는 동시에 response body도 보댓
            dataType: 'json',
            contentType:'application/json; charset=utf-8', // JSON 객체로 보내기 때문에 타입 설정
            data: JSON.stringify(data)  // 생성을 위한 정보의 객체를 JSON으로 바꿔서 데이터로 보냄
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    commentSave: function(){
        var data = {
            postId: $('#comment_postId').val(),
            content: $('#comment_content').val(),
            userId: $('#comment_userId').val(), //사용자 일련번호
        };
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/api/v1/comments', //포스트 작성하는 api로 명령보내는 동시에 response body도 보냄
            dataType: 'json',
            contentType:'application/json; charset=utf-8', // JSON 객체로 보내기 때문에 타입 설정
            data: JSON.stringify(data)  // 생성을 위한 정보의 객체를 JSON으로 바꿔서 데이터로 보냄
        }).done(function() {
            alert('댓글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    postUpdate : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var postId = $('#id').val();
        var userId=$('#author').val();


        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+postId+'/users/'+userId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentUpdate : function () {
        var data = {
            content: $('#comment-content').val()
        };

        var commentPostId = $('#comment-postId').val();
        var commentUserId=$('#comment-author').val();


        $.ajax({
            type: 'PUT',
            url: '/api/v1/comments/posts/'+commentPostId+'/users/'+commentUserId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },




};

main.init();