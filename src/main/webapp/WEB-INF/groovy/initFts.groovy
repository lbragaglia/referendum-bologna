import com.google.appengine.api.search.Consistency


File stradario = new File("WEB-INF/stradario.csv")

if( !stradario.exists() ) {
  log.error "File does not exist"
  return
}


def i = 0
def commit = []
def from = params.from as Integer
stradario.eachLine { line ->
 if( i >= from && i < (from+1000) && line.trim() ) {
	//log.info "Reading: " + line
	def value = line.split( ',' ).collect { it.trim() }
	value[4] = i
	//log.info value.toString()
	commit << value
	
	if (i%100 == 0) {
		commitRequest(commit)
		commit = []
	}
 }
 i++
}
commitRequest(commit)


def commitRequest(commit) {
 if (commit.isEmpty()) {
	 return
 }
 log.info "FTS batch 100 commit"
 def index = search.index("stradario", Consistency.PER_DOCUMENT)
 def response = index.add {
  commit.each { value ->

   document(id: value[4], locale: Locale.ITALY) {
    indirizzo text: value[0]
	numeroSezione number: value[1] as Integer
    sezione text: value[2]
    indirizzoSezione text: value[3]
   }
  }
 }
 log.info response.toString()
}
	
	
/*
document(id: i++, locale: Locale.ITALY) {
  indirizzo text: "Via Ghiberti, 15"
  sezione text: "Centro ViaLarga"
  indirizzoSezione text: "Via del Carpentiere, 55"
}
document(id: i++, locale: Locale.ITALY) {
	indirizzo text: "Via Ghiberti, 13"
	sezione text: "Centro ViaLarga"
	indirizzoSezione text: "Via del Carpentiere, 55"
}
document(id: i++, locale: Locale.ITALY) {
	indirizzo text: "Via Ghiberti, 11"
	sezione text: "Centro ViaLarga"
	indirizzoSezione text: "Via del Carpentiere, 55"
}
document(id: i++, locale: Locale.ITALY) {
	indirizzo text: "Via Massarenti, 22"
	sezione text: "Centro Sociale Masi"
	indirizzoSezione text: "Via Conconi, 42"
}
document(id: i++, locale: Locale.ITALY) {
	indirizzo text: "Via Massarenti, 23"
	sezione text: "Centro Sociale Masi"
	indirizzoSezione text: "Via Conconi, 42"
}
*/
