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
<el-main>
    <NavMeniZaPacijenta/>
    <h2>Kreiraj Zalbu</h2>
    <div id="unos">
    <el-form :model="zalba" status-icon :rules="rules" ref="zalba" label-width="120px" class="demo-ruleForm">
    <el-form-item label="Naslov" prop="naslov">
        <el-input type="text" v-model="zalba.naslov" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="Tip žalbe:" prop="tipZalbe">
      <div class="grupa">
          <el-select  v-model="zalba.tipZalbe" @change="promjena()" placeholder="Select">
            <el-option
              v-for="item in opcije"
                :key="item.uloga"
                :label="item.label"
                :value="item.uloga">
            </el-option>
        </el-select>
      </div>        
    </el-form-item>
    <el-form-item label="Text" prop="text">
        <el-input type="textarea" v-model="zalba.text" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm('zalba')">Potvrda</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
    </el-form-item>
    </el-form>
    </div>

    <div>
        <el-table
      ref="singleTable"
      :data="tableData"
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        property="id"
        label="Id"
        width="120">
      </el-table-column>
      <el-table-column
        property="identifikator"
        label="Indetifikator"
        width="120">
      </el-table-column>
    </el-table>
    </div>
</el-main>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"

  export default {
    name: 'Zalbe',
    data() {
      return {
        zalba: {
          naslov: '',
          text: '',
          tipZalbe: '',
          idObjekta: ''
        },
         currentRow: null,
         tableData: [],
        opcije: [{
              uloga: 'Dermatolog',
              label: 'Dermatolog'
            }, {
              uloga: 'Farmaceut',
              label: 'Farmaceut',
            }, {
              uloga: 'Apoteka',
              label: 'Apoteka'
            }
            ],
        rules: {
          naslov: [
            { required: true, message: 'Unesite naslov!', trigger: 'blur' }
          ],
          text: [
            { required: true, message: 'Unesite text!', trigger: 'blur' }
          ],
          tipZalbe: [
            { required: true, message: 'Unesite tip žalbe!', trigger: 'blur' }
          ]
        }
      };
    },
    components : {
       NavMeniZaPacijenta
    },
    props: [],

    methods: {
        open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno kreirana zalba.',
          type: 'success'
        });
      },
      open2() {
        this.$message({
          showClose: true,
          message: 'Morate izabrati nesto.',
          type: 'error'
        });
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid && this.currentRow != null){
                this.$store.dispatch('APKorisnici/kreirajZalbu', 
                {naslov: this.zalba.naslov, text: this.zalba.text, 
                datumVrijeme: new Date(), tipZalbe: this.zalba.tipZalbe, idObjekta: this.currentRow.id})
                .then(response => {
                    console.log(response);
                    this.open1();
                });
            } else {
              this.open2();
            }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      promjena(){
        this.$store.dispatch('APKorisnici/dobaviKandidateZaZalbu', this.zalba.tipZalbe)
        .then(response => {
          this.tableData = response.data;
        });
      },
      setCurrent(row) {
        this.$refs.singleTable.setCurrentRow(row);
      },
      handleCurrentChange(val) {
        console.log(val);
        this.currentRow = val;
      }
    }
  }
</script>