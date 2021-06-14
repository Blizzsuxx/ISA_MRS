<style scoped>
  body {
    margin: 10;
    padding: 10;
    background-color: #17a2b8;
    height: 100vh;
  }

  .grupa {
    width: 200px;
  }

  #unos {
    margin: auto;
    width: 50%;
    padding: 10px;
    
  }
  #unos .container #unos-row #unos-column #unos-box {
    margin: auto;
    width: 50%;
    margin-top: 10px;
    max-width: 750px;
    height: 300px;
    border: 1px solid #9C9C9C;
    background-color: #EAEAEA;
    font-weight: bold;
  }

  #unos .container #unos-row #unos-column #unos-box #unos-form {
    padding: 20px;
  }

  #unos-link {
    font-weight: normal;
    font-style: italic;
  }
</style>

<template>
  <div id="unos">
    <h2 class="text-center text-white pt-5">{{naslov}}</h2>
    <div class="container">
      <div id="unos-row" class="row justify-content-center align-items-center">
        <div id="unos-column" class="col-md-6">
          <div id="unos-box" class="col-md-12">
            <el-form ref="korisnik" :model="korisnik" :rules="rules" label-width="140px">
              <h3 class="text-center text-info">{{naslovForme}}</h3>
              <el-form-item label="Korisnicko ime:" prop="korisnickoIme">
                <div class="grupa">
                <el-input v-model="korisnik.korisnickoIme"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Sifra" prop="sifra">
                <div class="grupa">
                <el-input type="password" v-model="korisnik.sifra"></el-input>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit('korisnik')">Prijava</el-button>
                <el-button @click="resetForm('korisnik')">Obriši</el-button>
              </el-form-item>
              
            </el-form>
            <div id="unos-link" class="text-right">
              <a href="/ap/FormaKorisnika">Registruj se!</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Prijava',
    data() {    
      return {
        naslov: 'Prijava',
        naslovForme: 'Prijava',
        korisnik: {
          korisnickoIme: '',
          sifra: '',
        },
        rules: {
          korisnickoIme: [
            { required: true, message: 'Unesite korisnicko ime!', trigger: 'blur' }
          ],
          sifra: [
            { required: true, message: 'Unesite lozinku!', trigger: 'blur' }
          ],
          
        }
      };
    },
    methods: {
      open1() {
        this.$message({
          showClose: true,
          message: 'Nevalidni kredecijali!',
          type: 'error'
        });
      },
      open2() {
        this.$message({
          showClose: true,
          message: 'Profil nije potvrđen preko email-a.!',
          type: 'error'
        });
      },

      onSubmit(formName) {
        var s = {username: this.korisnik.korisnickoIme, password: this.korisnik.sifra }
        this.$store.dispatch('APKorisnici/validateLogin', s).then(
            response => {
                var s = JSON.parse(localStorage.getItem('user'));
                console.log(s);
                if (!s){
                  this.open1();
                  this.resetForm(formName);
                }  else {
                  if (s.uloga === 'ROLE_ADMIN_SISTEMA'){
                    this.$router.push('/ap/AdministratorSistema')
                  } else if (s.uloga === 'ROLE_ADMIN_APOTEKE' || 
                  s.uloga === "ROLE_ADMIN_APOTEKA"){
                    this.$store.dispatch("APKorisnici/potvrdaLozinke").then(response=>{
                      if(!response){
                        this.$router.push('/adminApoteke/potvrda')
                      }
                      else{
                         this.$router.push('/profilApoteke')
                      }
                    })
                   
                  } else if (s.uloga === 'ROLE_PACIJENT'){
                    this.$router.push('/ap/pacijent')
                  } else if (s.uloga === 'ROLE_DOBAVLJAC'){
                    this.$router.push('/ap/Dobavljac')
                  } else if (s.uloga === 'ROLE_DERMATOLOG'){
                    this.$router.push('/ap/dermatolog')
                  } else if (s.uloga === 'ROLE_FARMACEUT'){
                    this.$router.push('/ap/farmaceut')
                  }
                } 
                return response;
            },
            error => {
              if (error.response.status == 401){
                this.open1();
                this.resetForm(formName);
              } else {
                this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              }
                
            }
        );

      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>