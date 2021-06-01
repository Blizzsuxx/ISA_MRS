 <template>
  <teleport to="body">
    <div v-if="modalOpen" id="myModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <span @click="modalOpen = false" class="close">&times;</span>
          <h2>Kolicina leka</h2>
        </div>
        <div class="modal-body">
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="lijek"
          >
            <el-form-item label="Kolicina">
              <el-input-number v-model="num"  @change="handleChange" :min="1" :max="dostupno"></el-input-number>
            </el-form-item>
            
            <el-form-item>
              <el-button type="primary" @click="posaljiLek()"
                >Zakazi</el-button
              >
              
            </el-form-item>
          </el-form>
        </div>
        <div style="margin-top: 20px">
    <label>Odaberite datum preuzimanja</label>
   <el-form-item >
              <el-date-picker
                v-model="ocenjivac.value2"
                type="date"
                :shortcuts="shortcuts"
                
                align="right"
               >
              </el-date-picker>
            </el-form-item>
    
  </div>
      </div>
    </div>
  </teleport>
</template>

<script>
export default {
  name: "ModalniProzorKolicinaleka",
  data() {
    return {
      modalOpen: false,
      dostupno:0,
      ocenjivac: {
        id:"",
        apoteka: 0,
        value2:"",
        kolicina: 0
      },
    };
  },
  methods: {
      handleChange(value){
          this.ocenjivac.kolicina=value
      },
      async posaljiLek(){
          console.log(this.ocenjivac.value2)
         var podaci=this.ocenjivac.value2+" "+this.ocenjivac.id+" "+this.ocenjivac.apoteka+" "+this.ocenjivac.kolicina;
        console.log(podaci)
        this.$store.dispatch("APlijekovi/rezervisiLek",podaci).then(response=>{

           if(response==true){
             this.$store.dispatch("Mail/posaljiMail", {"text": "Rezervisali ste lek  iz apoteke: ", "address" : "rajtarovnatasa@gmail.com"})
     
           }else{
           this.$message({
                type: 'danger',
                message: 'Rezervacija nije uspela.'
              });
           }
         })
         


          
      }
  },
  mount() {
  
  },
};
</script>

<style scoped>
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 300px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 30%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 10px 16px;
  background-color: #409eff;
  color: white;
}

.modal-body {
  padding: 2px 16px;
}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>