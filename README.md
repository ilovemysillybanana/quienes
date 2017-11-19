# Quiénes

Why this name? For those of you who took a language in high school, it might be obvious. For those did not, it just means, "who" in Spanish.

## Why does this exist?

I was inspired to create this over a weekend when parsing horrible amounts of text given to a colleague and myself from multiple platforms using multiple methods that generated differing outputs. These files were just different enough to bring us great heartache.

## Goals

- Support AIX, Windows, Solaris, and Linux Operating Systems
- Return the output in a neatly formatted JSON File
- Never cry again

## Working

- AIX? ( I am not able to test this. )
- Linux
- Solaris
- Windows

## Instructions

Run the jar as if it were any other java application, you can send the output anywhere you'd like using standard terminal functions.

```
java -jar quienes.jar
# redirect output
java -jar quienes.jar > output.json
```

## Expected Output

```
{
  "userdata": {
    "root": ["root"],
    "daemon": ["daemon"],
    "bin": ["bin"],
    "sys": ["sys"],
    "sync": [],
    "games": ["games"],
    "man": ["man"],
    "lp": ["lp"],
    "mail": ["mail"],
    "news": ["news"],
    "uucp": ["uucp"],
    "proxy": ["proxy"],
    "www-data": ["www-data"],
    "backup": ["backup"],
    "list": ["list"],
    "irc": ["irc"],
    "gnats": ["gnats"],
    "nobody": [],
    "systemd-timesync": ["systemd-timesync"],
    "systemd-network": ["systemd-network"],
    "systemd-resolve": ["systemd-resolve"],
    "systemd-bus-proxy": ["systemd-bus-proxy"],
    "syslog": ["syslog"],
    "_apt": [],
    "messagebus": ["messagebus"],
    "uuidd": ["uuidd"],
    "lightdm": ["lightdm"],
    "whoopsie": ["whoopsie"],
    "avahi-autoipd": ["avahi-autoipd"],
    "avahi": ["avahi"],
    "dnsmasq": [],
    "colord": ["colord"],
    "speech-dispatcher": [],
    "hplip": [],
    "kernoops": [],
    "pulse": ["audio", "pulse"],
    "rtkit": ["rtkit"],
    "saned": ["scanner", "saned"],
    "usbmux": [],
    "jose": ["cdrom", "sudo", "dip", "plugdev", "lpadmin", "jose", "sambashare", "vboxusers"],
    "postgres": ["ssl-cert", "postgres"],
    "sbt": ["sbt"],
    "openldap": ["openldap"],
    "rundeck": ["rundeck"]
  }
}
```

## Contributions

If you're interested in this project, you can help by writing tests, testing on different platforms, sending issues. Really any and all help is appreciated. Let's never cry again together. 
