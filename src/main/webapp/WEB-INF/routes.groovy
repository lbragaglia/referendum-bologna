//get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
//get "/", forward: "/WEB-INF/pages/index.gtpl"
//get "/", forward: "/WEB-INF/pages/frameset.gtpl"
//get "/index", forward: "/WEB-INF/pages/index.gtpl"
//get "/", forward: "/WEB-INF/pages/frameset.gtpl"
//get "/", forward: "/WEB-INF/pages/frameset.gtpl"
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/lookup", forward: "/lookup.groovy"//, cache: 3.hours
get "/init", forward: "/init.groovy"
put "/initFts", forward: "/initFts.groovy"
all "/[^_]**", redirect: "/"
//get "/_ah**", validate: { users.isUserAdmin() }
