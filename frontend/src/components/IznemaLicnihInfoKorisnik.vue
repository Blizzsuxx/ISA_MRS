<template>
    <div>
        <label>JMBG:</label>
	<input type="text" v-model="selectedStudent.jmbg" /> <br />
	<label>Ime:</label>
	<input name="ime" type="text" v-model="selectedStudent.ime" v-bind:disabled="mode=='BROWSE'" /> <br />
	<label>Prezime:</label>
	<input type="text" v-model="selectedStudent.prezime" v-bind:disabled="mode=='BROWSE'" /> <br />
	<label>Datum rođenja:</label>
	<vuejs-datepicker v-model="selectedStudent.datumRodjenja" format="dd.MM.yyyy" v-bind:disabled="mode=='BROWSE'"></vuejs-datepicker> <br/>
	<!-- <input type="date" v-model="selectedStudent.datumRodjenja" v-bind:disabled="mode=='BROWSE'" /> <br /> -->
	
	<button v-on:click="updateStudent(selectedStudent)" v-bind:disabled="mode=='BROWSE'">Save</button>
	<button v-on:click="cancelEditing" v-bind:disabled="mode=='BROWSE'">Cancel</button> <br />
	
    </div>
</template>

<script>
    
    export default {
        name: 'Profil',
        data () {
            return {
        selectedStudent: {},
        searchField: ""
            }
        },
        beforeRouteUpdate (to, from, next) {
           // this.fetchUser(to.params.username)

           // next()
        },
        computed: {
            /*isLoggedIn () {
                return !!this.authUser
            }*/
        },
        created () {
          /*  this.fetchUser(this.$route.params.username)

            const token = localStorage.getItem('tweetr-token')

            if (token) {
                this.fetchAuthenticatedUser(token)
            }*/
        },
         mounted () {
        //axios
          //.get('rest/korisnici/getOne')
          //.then(response => (this.selectedStudent = fixDate(response.data)))
    },
        methods: {
            fetchUser (username) {
              //  axios.get(`/${username}`).then(response => {
                //    this.user = response.data.data
                  //  this.tweets = response.data.data.tweets.reverse()
               // })
            },
            fetchAuthenticatedUser (token) {
                /*axios
                    .get('account/me', {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    })
                    .then(response => {
                        this.authUser = response.data.data
                    })*/
            },

        editStudent : function() {
    		/*if (this.selectedStudent.jmbg == undefined)
    			return;
    		this.backup = [this.selectedStudent.ime, this.selectedStudent.prezime, this.selectedStudent.brojIndeksa, this.selectedStudent.datumRodjenja];
    		this.mode = 'EDIT';*/
    	},
    	updateStudent : function(student) {
    		/*var s = {jmbg:student.jmbg, ime:student.ime, prezime:student.prezime, datumRodjenja:student.datumRodjenja.getTime(), brojIndeksa:student.brojIndeksa};
    		axios
    		.post("rest/studenti/updatejson", s)
    		.then(response => toast('Student ' + student.ime + " " + student.prezime + " uspešno snimljen."));
    		this.mode = 'BROWSE';*/
    	},
    	cancelEditing : function() {
    		/*this.selectedStudent.ime = this.backup[0];
    		this.selectedStudent.prezime = this.backup[1];
    		this.selectedStudent.brojIndeksa = this.backup[2];
    		this.selectedStudent.datumRodjenja = this.backup[3];
    		this.mode = 'BROWSE';*/
    	}
    },
    components: {
      	vuejsDatepicker
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
    }
    }
</script>