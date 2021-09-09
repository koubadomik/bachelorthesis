
var path = require('path');

module.exports = {
    entry: './src/index.js',
    devtool: 'cheap-module-source-map',
    cache: true,

    devServer: {
        compress: true,
        inline: true,
        port: 3000,
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization",
            'Access-Control-Expose-Headers': "X-Requested-With, content-type, Authorization"
        }

    },
    output: {
        publicPath: '/dist',
        path: __dirname + "/dist",
        filename: "bundle.js"
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015']
                }
            },
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            }
        ]
    }
};