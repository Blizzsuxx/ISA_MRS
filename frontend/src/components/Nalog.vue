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
    height: 650px;
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
    <el-form :model="korisnik" status-icon :rules="rules" ref="korisnik" label-width="120px" class="demo-ruleForm">
    <el-form-item label="Korisnicko ime:" prop="korisnickoIme">          
        <el-input v-model="korisnik.korisnickoIme" readonly></el-input>          
    </el-form-item>
    <el-form-item label="Sifra" prop="sifra">          
        <el-input type="password" v-model="korisnik.sifra"></el-input>           
    </el-form-item>
    <el-form-item label="Potvrda Sifre:" prop="potvrdaSifre">
        <el-input type="password" v-model="korisnik.potvrdaSifre"></el-input>          
    </el-form-item>
    <el-form-item label="Ime:" prop="ime">
        <el-input v-model="korisnik.ime"></el-input>           
    </el-form-item>
    <el-form-item label="Prezime:" prop="prezime">
        <el-input v-model="korisnik.prezime" autocomplete="off"></el-input>         
    </el-form-item>
    <el-form-item label="Email:" prop="email">
        <el-input v-model="korisnik.email" autocomplete="off" readonly></el-input>       
    </el-form-item>
    <el-form-item label="Datum:" prop="rodjendan">       
        <el-date-picker type="date" placeholder="Datum rodjenja" v-model="korisnik.rodjendan" style="width: 100%;"></el-date-picker>          
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit('korisnik')">Potvrda</el-button>
        <el-button @click="resetForm('korisnik')">Reset</el-button>
    </el-form-item>
    </el-form>
</div>
</template>

<script>
import moment from 'moment'
  export default {
    name: 'Nalog',
    data() {
        return {
            korisnik: {
            korisnickoIme: '',
            sifra: '',
            potvrdaSifre: '',
            ime: '',
            prezime: '',
            rodjendan: '',
            email: '',
            },
            
        
         rules: {
          korisnickoIme: [
            { required: true, message: 'Unesite korisnicko ime!', trigger: 'blur' },
          ],
          ime: [
            { required: true, message: 'Unesite ime!', trigger: 'blur' }
          ],
          prezime: [
            { required: true, message: 'Unesite prezime!', trigger: 'blur' }
          ],
          rodjendan: [
            { required: true, message: 'Unesite datum rodjenja!', trigger: 'blur' },
            { type: 'date', message: 'Mora biti datum' }
          ],
          email: [
            { required: true, message: 'Unesite email adresu!', trigger: 'blur' },
            { type: 'email', message: 'Unesite validnu adresu!', trigger: ['blur', 'change'] }
          ]
        }
      }; 
    },


    mounted () {
        this.$store.dispatch('APKorisnici/dobaviTrenutnogKorisnika')
        .then(response => {
            this.korisnik = response;
            this.korisnik.sifra='';
        })
    },
    methods: {
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno azurirani podaci.',
          type: 'success'
        });
      },

      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid){
                var k = {korisnickoIme: this.korisnik.korisnickoIme, sifra: this.korisnik.sifra, 
                ime: this.korisnik.ime, prezime: this.korisnik.prezime, rodjendan:moment(String(this.korisnik.rodjendan)).format('YYYY-MM-DD hh:mm'),
                email: this.korisnik.email, uloga: this.korisnik.uloga};
                this.$store.dispatch('APKorisnici/azurirajNalog', k)
                .then(response => {
                    this.korisnik = response.data;
                    this.korisnik.sifra = '';
                    this.korisnik.potvrdaSifre = '';
                    this.open1();
                })
            }
        });
          
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      },
    }
  }
</script>