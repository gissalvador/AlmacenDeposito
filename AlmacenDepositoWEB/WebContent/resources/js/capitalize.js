
        String.prototype.capitalize = function() {
            return this.toLowerCase().replace(/\b\w/g, function(m) {
                return m.toUpperCase();
            });
        };

        function chiquito(val){
            var myInput = document.getElementById('form:nombre');
            var myOutPut = myInput.value.capitalize();
            myInput.value = myOutPut;
            
        }
 