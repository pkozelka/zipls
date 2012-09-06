all: clean package

clean:
	rm -rf target/

package:
	mkdir --parent target/classes
	javac -d target/classes -sourcepath src `find * -name '*.java'`
	echo 'Manifest-Version: 1.0\r\nMain-Class: net.kozelka.zip.ZipDeepLister\r\n' >target/MANIFEST.MF
	jar cvfm target/zipls.jar target/MANIFEST.MF -C target/classes/ .

run:
	java -jar target/zipls.jar test/sample.zip
