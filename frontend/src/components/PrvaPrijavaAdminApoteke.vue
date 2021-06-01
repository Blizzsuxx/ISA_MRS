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
<el-form status-icon  label-width="120px" class="demo-ruleForm">
  <el-form-item label="Lozinka" prop="pass">
    <el-input type="password" v-model="pass" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="Potvrda Lozinke" prop="checkPass">
    <el-input type="password" v-model="confPass" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item>
    
    
      
     <el-button type="primary" @click="submitForm">Potvrda</el-button>
    
  </el-form-item>
</el-form>
</div>
</template>

<script>
  export default {
    name: 'AdminApotekePotvrdaLozinke',
    data() {
      return {
        pass:"",
        confPass:"",
      };
    },
    methods: {
      submitForm() {
        if(this.pass === this.confPass){
            this.$store.dispatch('APKorisnici/promjenaLozinke', this.pass)
            this.$router.go(-1)
            
        }
        else{
           this.$message({
                type: 'danger',
                message: 'Lozinke treba da se poklapaju.'
              });
              return;
        }
      },
     
    }
  }
</script>