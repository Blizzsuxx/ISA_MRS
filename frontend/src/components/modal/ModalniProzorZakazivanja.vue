<template>
  <teleport to="body">
    <div v-if="modalOpen" id="myModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <span @click="modalOpen = false" class="close">&times;</span>
          <h2>Zakazi pregled</h2>
        </div>
        <div class="modal-body">
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            
          >
            <el-form-item label="Pregled">
              <el-date-picker
                v-model="value2"
                type="datetimerange"
                :shortcuts="shortcuts"
                range-separator="To"
                start-placeholder="Pocetak"
                end-placeholder="Kraj"
                align="right">
              </el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="promjeniCijenu"
                >Zakazi</el-button
              >
              


  <el-select v-model="value" placeholder="Select">
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.pocetakTermina"
      :value="item.id"
      :disabled="item.disabled">
    </el-option>
  </el-select>
            </el-form-item>
          </el-form>
          
        </div>
        <!-- <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div> -->
    
      </div>
    </div>
  </teleport>
</template>

<script>
export default {
  name: "ModalniProzorZakazivanja",
  data() {
    return {
      modalOpen: false,
      value2: '',
      korisnik : null,
      pregledID : this.$route.params.pregledID,
      options : this.$store.state.APPosete.slobodnePoseteD,
      value : '',
      radnik : {pregledi : ["aaa"]},
        shortcuts: [{
          text: 'Sad odmah',
          value: (() => {
            const end = new Date();
            const start = new Date();
            this.radnik = {pregledi : ["aaa"]};
            let arrayLength = this.radnik.pregledi.length;
            var prevItem = this.radnik.pregledi[0];
            var freeTime = 0;
            for (var i = 1; i < arrayLength; i++) {
                console.log(this.radnik.pregledi[i]);

                
                let item = this.radnik.pregledi[i];
                if (item.start.getTime() - prevItem.end.getTime() >= 10){
                  freeTime = item.end;
                  break;
                }
                prevItem = item;
                //Do something
            }
            if(freeTime == 0){
              freeTime = new Date();
            }
            start.setTime(freeTime.getTime());
            end.setTime(start.getTime() + 3600 * 500);
            console.log("AAAAAAAAAAAAAA")
            return [start, end]
          })()
        }, {
          text: 'Za nedelju dana',
          value: (() => {
            const end = new Date();
            const start = new Date();
            let arrayLength = this.radnik.pregledi.length;
            var prevItem = this.radnik.pregledi[0];
            var freeTime = 0;
            for (var i = 1; i < arrayLength; i++) {
                console.log(this.radnik.pregledi[i]);
                let item = this.radnik.pregledi[i];
                if (item.start.getTime() - prevItem.end.getTime() >= 10 && (item.start.getTime()) - start.getTime() >= 3600 * 1000 * 24 * 7){
                  freeTime = item.end;
                  break;
                }
                prevItem = item;
                //Do something
            }
            if(freeTime == 0){
              freeTime = new Date();
              freeTime.setTime(freeTime.getTime() + 3600 * 1000 * 24 * 7);
            }
            
            start.setTime(freeTime.getTime());
            end.setTime(start.getTime() + 3600 * 500);
            return [start, end]
          })()
        }, {
          text: 'sledeceg meseca',
          value: (() => {
            const end = new Date();
            const start = new Date();
            let arrayLength = this.radnik.pregledi.length;
            var prevItem = this.radnik.pregledi[0];
            var freeTime = 0;
            for (var i = 1; i < arrayLength; i++) {
                console.log(this.radnik.pregledi[i]);
                let item = this.radnik.pregledi[i];
                if (item.start.getTime() - prevItem.end.getTime() >= 10 && (item.start.getTime()) - start.getTime() >= 3600 * 1000 * 24 * 30){
                  freeTime = item.end;
                  break;
                }
                prevItem = item;
                //Do something
            }
            if(freeTime == 0){
              freeTime = new Date();
              freeTime.setTime(freeTime.getTime() + 3600 * 1000 * 24 * 30);
            }
            start.setTime(freeTime.getTime());
            end.setTime(start.getTime() + 3600 * 500);
            return [start, end]
          })()
        }],
        
      
    };
  },
   async  mounted() {
     console.log(this.pregledID)
    await this.$store.dispatch("APPosete/nabaviSlobodneTermine", {"posetaId" : this.pregledID});
    console.log("a")
    this.options = this.$store.state.APPosete.slobodnePoseteD;
    console.log(this.options)
    console.log("BBBBBBBBBBBBBBBB")
  },
  methods: {
      promjeniCijenu(){
          console.log("andrija je najajci")
          this.$store.dispatch("APPosete/zakaziPosetu",{'slobodan termin':this.value, 'datetime': this.value2,'korisnik': this.korisnik,'radnik': this.radnik, "pregledID" : this.pregledID})
          this.$store.dispatch("Mail/posaljiMail", {"text": "Zakazan vam je pregled za " + this.value2, "address" : "mahajiraaji@gmail.com"})
      }
  },
 
};
</script>

<style scoped>
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
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
  width: 60%;
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