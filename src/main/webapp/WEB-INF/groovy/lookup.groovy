import com.google.appengine.api.search.Consistency;
import com.google.appengine.api.search.ScoredDocument;
import groovy.json.*;

/*
File stradario = new File("WEB-INF/stradario.csv")

if( !stradario.exists() ) {
  log.error "File does not exist"
  return
}
*/

def stradario = application.getAttribute("stradario")
if (stradario == null || stradario.isEmpty()) {
	File sf = new File("WEB-INF/stradario.csv")
	
	if( !sf.exists() ) {
	  log.error "File does not exist"
	  return
	}
	def sm = []
	log.info ">>> Caricamento stradario"
	sf.splitEachLine(','){ sm << it }
	application.setAttribute("stradario", sm)

	stradario = sm	
	log.info ">>> Caricato stradario"
}

def i = 0
response.contentType = 'application/json'
def json = new JsonBuilder()
log.info ">>>>>> Inizio ricerca"
def results = []

//if (params.q != null && params.q.trim() != "") {

//json {
//	indirizzi(
		stradario.each { value ->

			if (i > 50) {
				return
			}
			
//			if (line.trim().size() == 0) {
//				return
//			}
			
//			def value = line.split( ',' ).collect { it.trim() }

//			def parole = params.q.toLowerCase().split( "\\s+" ).collect { it.trim() }
//			log.info ">>>>>>>>>>>> parole: " + parole
			def parola = params.q
//			for (parola in parole) {
				if (!value[0].toLowerCase().contains(parola.toLowerCase())) {
					return
				}
//			}
			
			log.info "Documento trovato $value[0]"
			results << [
				id: i++,
				indirizzo: value[0],
				sezione: value[2],
				numeroSezione: value[1],
				indirizzoSezione: value[3]
			]
		}
//	)
//}


//}
log.info ">>>>>>> Fine ricerca"

log.info ">>>>>>> Inizio JSON"
json {
	indirizzi(
		results
	)
}
log.info ">>>>>>> Fine JSON"

/*
def index = search.index("stradario", Consistency.PER_DOCUMENT)

// search the index
Closure indexSearch = memcache.memoize { String query ->
	index.search("indirizzo:"+query)
}
def results = indexSearch(params.q)
*/

// iterate over all the resuts
/*
results.each { ScoredDocument doc ->
  doc.sezione
  doc.indirizzo
  doc.indirizzoSezione
}
*/

/*
def i = 0
response.contentType = 'application/json'
def json = new JsonBuilder()
json {
	indirizzi(
		results.collect { ScoredDocument doc ->
			log.info "Documento trovato $doc.indirizzo"
			[
				id: doc.id,
				indirizzo: doc.indirizzo,
				sezione: doc.sezione,
				numeroSezione: (doc.fieldNames.contains("numeroSezione") ? doc.numeroSezione : ""),
				indirizzoSezione: doc.indirizzoSezione
			]
		}
	)
}
*/

out << json
