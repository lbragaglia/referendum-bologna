
File stradario = new File("WEB-INF/stradario.csv")

if( !stradario.exists() ) {
  log.error "File does not exist"
  return
}

memcache.clearCacheForUri('/lookup')

def stradario_memory = []
stradario.splitEachLine(','){ stradario_memory << it }
application.setAttribute("stradario", stradario_memory)

log.info "Caricato stradario"

/*
def i = 0
stradario.eachLine {
	if (i%1000 == 0) {
		defaultQueue.add countdownMillis: i*10, url: "/initFts",
			taskName: "initFts"+i,
			method: 'PUT', params: [from: i]
	}
	i++
}
*/