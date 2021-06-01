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
  border: 1px solid #9c9c9c;
  background-color: #eaeaea;
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
    <el-form status-icon label-width="120px" class="demo-ruleForm">
      <el-form-item label="Stara lozinka" prop="pass">
        <el-input type="password" v-model="old_pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Nova lozinka" prop="pass">
        <el-input type="password" v-model="pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Potvrda lozinke" prop="checkPass">
        <el-input
          type="password"
          v-model="confPass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">Potvrda</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "PromjenaLozinke",
  data() {
    return {
      old_pass: "",
      pass: "",
      confPass: "",
    };
  },
  methods: {
    submitForm() {
      this.$store
        .dispatch("APKorisnici/provjeraLozinke", this.old_pass)
        .then((response) => {
            console.log(response)
          if (response) {
             
            if (this.pass === this.confPass) {
                console.log(this.pass)
                console.log(this.confPass)
              this.$store.dispatch("APKorisnici/promjenaLozinke", this.pass);
              this.$router.go(-1);
            } else {
              this.$message({
                type: "danger",
                message: "Lozinke treba da se poklapaju.",
              });
              return;
            }
          }
          else{
             this.$message({
                type: "danger",
                message: "Trenuta lozinka nije ispravna.",
              }); 
          }
        });
    },
  },
};
</script>