
module.exports = {
	paths: function (paths, env) {

		// use this to check original paths:
		// console.log(paths)


		const root = paths.appPath
		paths.appBuild = `${root}/src/main/resources/web-app`
		paths.appPublic = `${root}/src/main/js/public`
		paths.appHtml = `${root}/src/main/js/public/index.html`
		paths.appIndexJs = `${root}/src/main/js/index.js`
		paths.appPackageJson = `${root}/package.json`
		paths.appSrc = `${root}/src/main/js`
		// paths.appTsConfig = `${root}/tsconfig.json`
		// paths.appJsConfig = `${root}/jsconfig.json`
		// paths.yarnLockFile = `${root}/yarn.lock`
		paths.testsSetup = `${root}/src/test/js/setupTests.js`
		// paths.proxySetup = `${root}/src/main/js/setupProxy.js`
		 paths.appNodeModules = `${root}/node_modules`
		// paths.swSrc = `${root}/src/main/js/service-worker.js`
		// paths.publicUrlOrPath = '/'
		paths.ownPath = `${root}/node_modules/react-scripts`
		paths.ownNodeModules = `${root}/node_modules/react-scripts/node_modules`
		// paths.appTypeDeclarations = `${root}/src/react-app-env.d.ts`
		// paths.ownTypeDeclarations = `${root}/node_modules/react-scripts/lib/react-app.d.ts`

		return paths;
	},

	// more to come below
}