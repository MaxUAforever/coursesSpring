
var courseApi = Vue.resource('/allcourses{/id}');

Vue.component('theme-row', {
    props: ['theme'],
    template: '<a href="">- {{ theme }}</a>'
    //template: '<a href="/allcourses(theme= {{ theme }})">- {{ theme }}</a>'
});

Vue.component('themes-list', {
    props: ['themes'],
    template: '<div><theme-row v-for="theme in themes" :key="theme" :theme="theme"/></div>',
    created: function() {
        courseApi.get().then(
            result => result.json().then(
                data => data['themes'].forEach(theme => this.themes.push(theme))
            )
        )
    }
});

Vue.component('course-row', {
    props: ['course'],
    template: '<div id="courseCard">\n' +
        '                <div class="centerPart">\n' +
        '                    <h2 id="courseNameAndDescr">{{ course.courseName }}:{{ course.theme }}</h2>\n' +
        '                    <div id="descr">{{ course.description }}</div>\n' +
        '                    <br/>\n' +
        '                </div>\n' +
        '\n' +
        '                <hr class="cardSplit">\n' +
        '\n' +
        '                <div class="lectAuthor">\n' +
        '                    <img id="lecturerIcon" src="img/allcourses/avatar.png" alt="">\n' +
        '                    <label id="lecturerName"><b>Lecturer: {{ course.lecturer }}</b></label>\n' +
        '                </div>\n' +
        '                <div class = "pageButt">\n' +
        '                    <form class="showMoreButton" action="/course(courseId={{ course.id }})">\n' +
        '                            <input type="hidden" name="courseId" value="{{ course.id }}">\n' +
        '                            <button type="submit" id="showMoreButt">Show more</button>\n' +
        '                    </form>\n' +
        '                </div>\n' +
        '      </div>'
});

Vue.component('courses-list', {
    props: ['courses'],
    template: '<div><course-row v-for="course in courses" :key="course.id" :course="course"/></div>',
    created: function() {
        courseApi.get().then(
            result => result.json().then(
                data => data['courses'].forEach(course => this.courses.push(course))
            )
        )
    }
});

var app1 = new Vue({
    el: '#themes',
    template: '<themes-list :themes="themes" />',
    data: {
        themes: []
    }
});

var app2 = new Vue({
    el: '#courses',
    template: '<courses-list :courses="courses" />',
    data: {
        courses: []
    }
});